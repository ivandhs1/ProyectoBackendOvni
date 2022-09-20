package com.grupo2.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.CarritoClienteVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.ProductoVo;
import com.grupo2.springboot.backend.apirest.entity.Rol;
import com.grupo2.springboot.backend.apirest.entity.Usuario;
import com.grupo2.springboot.backend.apirest.services.administrador.IAdministradorService;
import com.grupo2.springboot.backend.apirest.services.usuarios.IUsuarioCrud;
import com.grupo2.springboot.backend.apirest.services.usuarios.IUsuarioService;

@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiAdmin")
public class AdministradorRestController {

	@Autowired
	private IAdministradorService adminService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioCrud usuarioCrud;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/admins")
	public ResponseEntity<?> admins(){
		List<AdministradorVo> administradores = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			administradores = adminService.findAll();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println(administradores);
		
		return new ResponseEntity<List<AdministradorVo>>(administradores,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiAdmin/admin/correo
	@GetMapping("/admin/{correo}")
	public ResponseEntity<?>  getAdmin(@PathVariable String correo){
		AdministradorVo admin=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			admin = adminService.findByCorreo(correo);
			if(admin==null) {
				response.put("mensaje","no se encontro el admin");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<AdministradorVo>(admin,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiAdmin/registro
	@PostMapping("/registro")
	public ResponseEntity<?> saveAdmin(@RequestBody AdministradorVo admin){
		System.out.println("entro al registro de admin");
		AdministradorVo adminNew = null;
		
		Map<String, Object> response = new HashMap<>();
		try {

			System.out.println(adminNew);
			admin.setEstado("1");
			String passwordBcrypt = passwordEncoder.encode(admin.getPasswordAdmin());
			admin.setPasswordAdmin(passwordBcrypt);
			adminNew = adminService.save(admin);
			Usuario user = new Usuario();
			user.setUsername(adminNew.getCorreoAdmin());
			user.setPassword(adminNew.getPasswordAdmin());
			user.setRol(Rol.ROLE_ADMIN);
			Usuario usuarioNew = usuarioCrud.registrarUsuario(user);
			adminNew.setUser(usuarioNew);
			adminNew = adminService.save(adminNew);
			
		}catch(DataAccessException e) {
			response.put("mensaje","Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","el administrador se ha creado con exito");
		response.put("Administrador", adminNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	// http://localhost:8080/apiAdmin/admin/correo
	@PutMapping("/admin/{correo}")
	public ResponseEntity<?> updateAdmin(@RequestBody AdministradorVo admin, @PathVariable String correo){
		AdministradorVo adminActual = adminService.findByCorreo(correo);
		AdministradorVo adminUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			adminActual.setNombreAdmin(admin.getNombreAdmin());
			adminActual.setApellidoAdmin(admin.getApellidoAdmin());
			adminActual.setDireccionAdmin(admin.getDireccionAdmin());
			adminActual.setTelefonoAdmin(admin.getTelefonoAdmin());
			
			String passwordBcrypt = passwordEncoder.encode(admin.getPasswordAdmin());
			adminActual.setPasswordAdmin(passwordBcrypt);
			Usuario usuarioActual = new Usuario();
			
			usuarioActual = usuarioService.findByUsername(correo);
			
			adminUpdated = adminService.save(adminActual);
			usuarioActual.setPassword(passwordBcrypt);
			usuarioService.save(usuarioActual);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","el admin ha sido actualizado con exito");
		response.put("admin", adminUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/estado/{correo}")
	public ResponseEntity<?> updateEstado(@PathVariable String correo){
		Map<String,Object> response = new HashMap<>();
		String estado = "";
		try {
			AdministradorVo administrador= adminService.findByCorreo(correo);
			estado = administrador.getEstado();
			if(estado.equals("1")) {
				administrador.setEstado("0");
			}else {
				administrador.setEstado("1");
			}
			adminService.save(administrador);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(estado.equals("1")) {
			response.put("mensaje", "el administrador fue deshabilitado");
		}else {
			response.put("mensaje", "el administrador fue habilitado");
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{correo}")
	public ResponseEntity<?>  getUsuario(@PathVariable String correo){
		Usuario usuario=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			usuario = usuarioCrud.findByUsername(correo);
			if(usuario==null) {
				response.put("mensaje","no se encontro el usuario");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
	}


}
