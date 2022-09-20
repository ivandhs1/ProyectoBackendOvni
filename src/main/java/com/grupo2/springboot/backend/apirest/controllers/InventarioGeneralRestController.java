package com.grupo2.springboot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioDetallesVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioGeneralVo;
import com.grupo2.springboot.backend.apirest.services.inventariodetalles.IinventarioDetallesService;
import com.grupo2.springboot.backend.apirest.services.inventariogeneral.IinventarioGeneralService;

@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiInventario")
public class InventarioGeneralRestController {
	
	@Autowired
	private IinventarioDetallesService inventarioDetallesService;
	
	@Autowired
	private IinventarioGeneralService inventarioGeneralService;
	
	//http://localhost:8080/apiInventario/inventarioGeneralCompleto
	@GetMapping("/inventarioGeneralCompleto")
	public ResponseEntity<?>inventarioGeneralCompleto(){
		List<InventarioGeneralVo> inventarioGeneral = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			inventarioGeneral = inventarioGeneralService.findAll();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<InventarioGeneralVo>>(inventarioGeneral,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiInventario/inventarioDetallesCompleto
	@GetMapping("/inventarioDetallesCompleto")
	public ResponseEntity<?>inventarioDetallesCompleto(){
		List<InventarioDetallesVo> inventarioDetalles = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			inventarioDetalles = inventarioDetallesService.findAll();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<InventarioDetallesVo>>(inventarioDetalles,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiInventario/InvenarioGeneralId/id
	@GetMapping("/inventarioGeneralId/{id}")
	public ResponseEntity<?>  getRegistroInvetarioGeneral(@PathVariable Integer id){
		InventarioGeneralVo inventarioGeneralIndividual=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			inventarioGeneralIndividual = inventarioGeneralService.findById(id);
			if(inventarioGeneralIndividual==null) {
				response.put("mensaje","no se encontro el registro en inventario General");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<InventarioGeneralVo>(inventarioGeneralIndividual,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiInventario/InvenarioDetallesId/id
	@GetMapping("/inventarioDetallesId/{id}")
	public ResponseEntity<?>  getRegistroInvetarioDetalles(@PathVariable Integer id){
		InventarioDetallesVo inventarioDetallesIndividual=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			inventarioDetallesIndividual = inventarioDetallesService.findById(id);
			if(inventarioDetallesIndividual==null) {
				response.put("mensaje","no se encontro el registro en inventario Detalles");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<InventarioDetallesVo>(inventarioDetallesIndividual,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiInventario/InvenarioGeneralProducto/producto
	@GetMapping("/inventarioGeneralProducto/{producto}")
	public ResponseEntity<?>  getRegistroInvetarioGeneralProducto(@PathVariable Integer producto){
		InventarioGeneralVo inventarioGeneralIndividual=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			inventarioGeneralIndividual = inventarioGeneralService.findByProducto(producto);
			if(inventarioGeneralIndividual==null) {
				response.put("mensaje","no se encontro el registro en inventario General");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<InventarioGeneralVo>(inventarioGeneralIndividual,HttpStatus.OK);
	}
	
	//http://localhost:8080/apiInventario/inventarioGeneralCompleto/cantidad
	@GetMapping("/inventarioGeneralCompleto/cantidad")
	public ResponseEntity<?>inventarioGeneral(){
		List<InventarioGeneralVo> inventarioGeneral = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			inventarioGeneral = inventarioGeneralService.findAllorden();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<InventarioGeneralVo>>(inventarioGeneral,HttpStatus.OK);
	}
	
	//http://localhost:8080/apiInventario/inventarioGeneralCompleto/positivo
	@GetMapping("/inventarioGeneralCompleto/positivo")
	public ResponseEntity<?>inventarioGeneralPositivo(){
		List<InventarioGeneralVo> inventarioGeneral = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			inventarioGeneral = inventarioGeneralService.findAllPositvos();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<InventarioGeneralVo>>(inventarioGeneral,HttpStatus.OK);
	}
	
	//http://localhost:8080/apiInventario/inventarioGeneralCompleto/positvoFiltrado/{term}
	@GetMapping("/inventarioGeneralCompleto/positvoFiltrado/{term}")
	public ResponseEntity<?>inventarioGeneralPositivoFiltrado(@PathVariable String term){
		System.out.println("AAAAAAAAAAAAAAAA");
		List<InventarioGeneralVo> inventarioGeneral = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			inventarioGeneral = inventarioGeneralService.findAllPositvosFiltrado(term);
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<InventarioGeneralVo>>(inventarioGeneral,HttpStatus.OK);
	}
	
	//http://localhost:8080/apiInventario/inventarioGeneralCompleto/positvoFiltradoNombre/{term}
	@GetMapping("/inventarioGeneralCompleto/positvoFiltradoNombre/{term}")
	public ResponseEntity<?>inventarioGeneralPositivoFiltradoNombre(@PathVariable String term){
		List<InventarioGeneralVo> inventarioGeneral = null;
		Map<String, Object> response = new HashMap<>();
		try {
			inventarioGeneral = inventarioGeneralService.findByCantidadPositivaFiltradoNombre(term);
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<InventarioGeneralVo>>(inventarioGeneral,HttpStatus.OK);
	}

	@GetMapping("/inventarioGeneralCompleto/destacado")
	public ResponseEntity<?> findDestacado(){
		InventarioGeneralVo inventarioGeneralIndividual=null;
		List<Integer> destacado = null; 
		List<InventarioGeneralVo> inventarioDestacados = new ArrayList<>(); 
		Map<String, Object> response = new HashMap<>();
		try {
			destacado = inventarioGeneralService.findDestacado();
			System.out.println(destacado.size());
			for(int i=0; i<destacado.size(); i++) {

				inventarioGeneralIndividual = inventarioGeneralService.findByProducto(destacado.get(i));
				if(inventarioDestacados.size()==3) {
					break;
				}else {
					if(inventarioGeneralIndividual.getCantidadProducto()!=0) {
						inventarioDestacados.add(inventarioGeneralIndividual);
					}

					inventarioGeneralIndividual=null;
				}

			}
			//inventarioGeneralIndividual = inventarioGeneralService.findByProducto(destacado.get(0));
			//System.out.println(inventarioGeneralIndividual.toString());
			System.out.println(inventarioDestacados);
		
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<InventarioGeneralVo>>(inventarioDestacados,HttpStatus.OK);
	}
	
}
