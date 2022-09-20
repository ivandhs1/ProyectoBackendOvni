package com.grupo2.springboot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.CarritoClienteVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.ItemCarritoVo;
import com.grupo2.springboot.backend.apirest.services.carritocliente.ICarritoClienteService;
import com.grupo2.springboot.backend.apirest.services.itemcarrito.IitemCarritoService;

@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiCarrito")
public class CarritoClienteRestController {
	
	@Autowired
	private ICarritoClienteService carritoService;
	
	@Autowired
	private IitemCarritoService itemCarritoService;
	
	// http://localhost:8080/apiCarrito/carrito/{id}
	@GetMapping("/carrito/{id}")
	public ResponseEntity<?> carrito(@PathVariable Integer id){
		CarritoClienteVo carrito = null;
		
		Map<String,Object>response = new HashMap<>();
		
		try {
			carrito = carritoService.findById(id);
			if(carrito==null) {
				response.put("mensaje","no se encontro el carrito");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CarritoClienteVo>(carrito,HttpStatus.OK);
	}

	//http://localhost:8080/apiCarrito/carrito/{id}
	@PutMapping("/carrito/{id}")
	public ResponseEntity<?> update(@RequestBody CarritoClienteVo carrito, @PathVariable Integer id){
		CarritoClienteVo carritoActual = carritoService.findById(id);
		CarritoClienteVo carritoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		
		try {
			carritoActual.setItemCarrito(carrito.getItemCarrito());
			carritoActual.setPrecioCarrito(0);
			carritoActual.getItemCarrito().forEach(t->{
				t.setPrecioItem();
				carritoActual.setPrecioCarrito(carritoActual.getPrecioCarrito() + t.getPrecioItem());
			});
			
			carritoActual.setCantidadCarrito(carrito.getCantidadCarrito());
			System.out.println(carrito.getCantidadCarrito());
			carritoUpdated = carritoService.save(carritoActual);
			itemCarritoService.deleteNull();
		}catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","el carrito ha sido actualizado con exito");
		response.put("carrito", carritoUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	//http://localhost:8080/apiCarrito/VaciarCarrito/{id}
	@PutMapping("/VaciarCarrito/{id}")
	public ResponseEntity<?> vaciar(@RequestBody CarritoClienteVo carrito, @PathVariable Integer id){
		CarritoClienteVo carritoActual = carritoService.findById(id);
		CarritoClienteVo carritoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		
		try {
			carritoActual.setItemCarrito(new ArrayList<ItemCarritoVo>());
			carritoActual.setPrecioCarrito(0);
			
			carritoActual.setCantidadCarrito(0);
			
			System.out.println(carrito.getCantidadCarrito());
			carritoUpdated = carritoService.save(carritoActual);
			itemCarritoService.deleteNull();
		}catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","el carrito ha sido actualizado con exito");
		response.put("carrito", carritoUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(){
		Map<String, Object> response = new HashMap<>();
		try {
			itemCarritoService.deleteNull();
		} catch (DataAccessException e) {
			response.put("Error", "se produjo un error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "se borro con exito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
