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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.ProductoVo;
import com.grupo2.springboot.backend.apirest.services.producto.IProductoService;

@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiProd")
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService;
	
	// http://localhost:8080/apiProd/productos
	@GetMapping("/productos")
	public List<ProductoVo> getProductos(){
		return productoService.findAll();
	}
	
	// http://localhost:8080/apiProd/producto/codigo
	@GetMapping("/producto/{codigo}")
	public ResponseEntity<?>  getProducto(@PathVariable int codigo){
		ProductoVo producto=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			producto = productoService.findByCodigo_producto(codigo);
			if(producto==null) {
				response.put("mensaje","No se encontro el producto");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoVo>(producto,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiProd/productoNombre/nombre
	@GetMapping("/productoNombre/{nombre}")
	public ResponseEntity<?>  getProductoNombre(@PathVariable String nombre){
		List<ProductoVo> productos = new ArrayList<ProductoVo>();
		
		Map<String,Object>response = new HashMap<>();
		try {
			productos = productoService.findByNombre(nombre);
			if(productos.size()<1) {
				response.put("mensaje","No se encontro el producto");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<ProductoVo>>(productos,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiProd/producto
	@PostMapping("/producto")
	public ResponseEntity<?> create(@RequestBody ProductoVo producto){
		ProductoVo productoNew = null;
		Map<String, Object> response = new HashMap<>();
		producto.setEstado("1");
		
		try {
			productoNew = productoService.save(producto);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","el producto se ha creado con exito");
		response.put("producto", productoNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	// http://localhost:8080/apiProd/producto/{codigo}
	@PutMapping("/producto/{codigo}")
	public ResponseEntity<?> update(@RequestBody ProductoVo producto,  @PathVariable int codigo){
		ProductoVo productoActual = productoService.findByCodigo_producto(codigo);
		ProductoVo productoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			productoActual.setNombreProducto(producto.getNombreProducto());
			productoActual.setPrecioProducto(producto.getPrecioProducto());
			productoActual.setPrecioProductoProveedor(producto.getPrecioProductoProveedor());
			productoActual.setDescripcionProducto(producto.getDescripcionProducto());
			productoActual.setFotoProducto(producto.getFotoProducto());
			
			productoUpdated = productoService.save(productoActual);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","el producto ha sido actualizado con exito");
		response.put("producto", productoUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

	// http://localhost:8080/apiProd/producto/estado/{codigo}
	@PutMapping("/producto/estado/{codigo}")
	public ResponseEntity<?> updateEstado(@PathVariable int codigo){
		Map<String,Object> response = new HashMap<>();
		String estado = "";
		try {
			ProductoVo producto = productoService.findByCodigo_producto(codigo);
			estado = producto.getEstado();
			if(estado.equals("1")) {
				producto.setEstado("0");
			}else {
				producto.setEstado("1");
			}
			productoService.save(producto);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(estado.equals("1")) {
			response.put("mensaje", "el producto fue deshabilitado");
		}else {
			response.put("mensaje", "el producto fue habilitado");
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiProd/producto/estado
	@GetMapping("/producto/estado")
	public ResponseEntity<?>  getProductoEstado(){
		List<ProductoVo> productos = new ArrayList<ProductoVo>();
		
		Map<String,Object>response = new HashMap<>();
		try {
			productos = productoService.findByEstado();
			if(productos.size()<1) {
				response.put("mensaje","No hay productos habilitados");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<ProductoVo>>(productos,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiProd/producto/estadoFiltro/{filtro}
		@GetMapping("/producto/estadoFiltro/{filtro}")
		public ResponseEntity<?>  getProductoEstadoFiltro(@PathVariable String filtro){
			List<ProductoVo> productos = new ArrayList<ProductoVo>();
			
			Map<String,Object>response = new HashMap<>();
			try {
				productos = productoService.findByEstadoFiltro(filtro);
				if(productos.size()<1) {
					response.put("mensaje","No hay productos con esta descripcion");
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} catch(DataAccessException e) {
				response.put("mensaje","error al realizar la consulta en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<List<ProductoVo>>(productos,HttpStatus.OK);
		}
}
