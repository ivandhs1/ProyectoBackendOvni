package com.grupo2.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.CarritoClienteVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;

import com.grupo2.springboot.backend.apirest.entity.Rol;
import com.grupo2.springboot.backend.apirest.entity.Usuario;
import com.grupo2.springboot.backend.apirest.services.carritocliente.ICarritoClienteService;

import com.grupo2.springboot.backend.apirest.services.cliente.IClienteService;

import com.grupo2.springboot.backend.apirest.services.usuarios.IUsuarioCrud;
import com.grupo2.springboot.backend.apirest.services.usuarios.IUsuarioService;

import com.grupo2.springboot.backend.apirest.util.service.IEnviosCorreo;
import com.grupo2.springboot.backend.apirest.util.service.RecuperarClass;

@CrossOrigin(origins = { "http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089" })
@RestController
@RequestMapping("/apiCliente")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired

	private IUsuarioCrud usuarioCrud;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ICarritoClienteService carritoService;

	@Autowired

	private IEnviosCorreo envioCorreo;

	// http://localhost:8080/apiCliente/clientes
	@GetMapping("/clientes")
	public ResponseEntity<?> clientes() {
		List<ClienteVo> clientes = null;
		Map<String, Object> response = new HashMap<>();
		try {
			clientes = clienteService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<ClienteVo>>(clientes, HttpStatus.OK);
	}

	// http://localhost:8080/apiCliente/cliente/correo
	@GetMapping("/cliente/{correo}")
	public ResponseEntity<?> getCliente(@PathVariable String correo) {
		ClienteVo cliente = null;

		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(correo);
			if (cliente == null) {
				response.put("mensaje", "no se encontro el cliente");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ClienteVo>(cliente, HttpStatus.OK);
	}

	@GetMapping("/clienteNombre/{nombre}")
	public ResponseEntity<?> getClienteBynombre(@PathVariable String nombre) {
		ClienteVo cliente = null;

		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findByNombre(nombre);

			if (cliente == null) {
				response.put("mensaje", "no se encontro el cliente");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ClienteVo>(cliente, HttpStatus.OK);
	}

	// http://localhost:8080/apiCliente/registro
	@PostMapping("/registro")
	public ResponseEntity<?> registro(@RequestBody ClienteVo cliente) {
		ClienteVo clienteNew = null;

		Map<String, Object> response = new HashMap<>();
		try {
			CarritoClienteVo carrito = new CarritoClienteVo();
			cliente.setCarrito(carrito);
			carrito.setCliente(cliente);

			String passwordBcrypt = passwordEncoder.encode(cliente.getPasswordCliente());
			cliente.setPasswordCliente(passwordBcrypt);
			clienteNew = clienteService.save(cliente);
			Usuario user = new Usuario();
			user.setUsername(clienteNew.getCorreoCliente());
			user.setPassword(clienteNew.getPasswordCliente());
			user.setRol(Rol.ROLE_CLIENTE);
			Usuario userNew = usuarioCrud.registrarUsuario(user);
			clienteNew.setUser(userNew);
			clienteNew = clienteService.save(clienteNew);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el ciente se ha creado con exito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// http://localhost:8080/apiCliente/cliente/correo
	@PutMapping("/cliente/{correo}")
	public ResponseEntity<?> update(@RequestBody ClienteVo cliente, @PathVariable String correo) {
		ClienteVo clienteActual = clienteService.findById(correo);
		ClienteVo clienteUpdated = null;
		Map<String, Object> response = new HashMap<>();

		try {
			clienteActual.setNombreCliente(cliente.getNombreCliente());
			clienteActual.setApellidoCliente(cliente.getApellidoCliente());
			clienteActual.setDireccionCliente(cliente.getDireccionCliente());
			clienteActual.setTelefonoCliente(cliente.getTelefonoCliente());

			String passwordBcrypt = passwordEncoder.encode(cliente.getPasswordCliente());
			clienteActual.setPasswordCliente(passwordBcrypt);
			Usuario usuarioActual = new Usuario();
			
			usuarioActual = usuarioService.findByUsername(correo);

			clienteUpdated = clienteService.save(clienteActual);
			usuarioActual.setPassword(passwordBcrypt);
			usuarioService.save(usuarioActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el cliente ha sido actualizado con exito");
		response.put("cliente", clienteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// http://localhost:8080/apiCliente/recuperar/{data}
	@PostMapping("/recuperar")
	public void mandarCorreo(@RequestBody RecuperarClass data) {
		envioCorreo.enviarVerificacion(data);
	}
	
	@GetMapping("/ayuda/{email}/{problema}/{descripcion}")
	public ResponseEntity<?> envioAyuda(@PathVariable String email,@PathVariable String problema, @PathVariable String descripcion ){
		Map<String, Object> response = new HashMap<>();
		String status= "";
		try {
			envioCorreo.enviarAyuda(email,problema,descripcion);
		}catch(DataAccessException e) {
			response.put("status", "Error");
			response.put("mensaje", "No se le ha podido enviar el correo al administrador, favor intente mas tarde.");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
		response.put("status", "Enviado");
		response.put("mensaje", "Se le notifico al administrador el cual le enviara un correo pronto.");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}