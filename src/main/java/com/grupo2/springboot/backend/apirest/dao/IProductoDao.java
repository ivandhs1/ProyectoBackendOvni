package com.grupo2.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.ProductoVo;

public interface IProductoDao extends CrudRepository<ProductoVo, Integer>{
	
	@Query("select producto from ProductoVo producto where producto.nombreProducto like %?1%")
	public List<ProductoVo> findByNombre(String term);
	
	@Query("select producto from ProductoVo producto where producto.estado = 1")
	public List<ProductoVo> findByEstado();
	
	@Query("select producto from ProductoVo producto where producto.estado = 1 and producto.descripcionProducto like %?1%")
	public List<ProductoVo> findByEstadoFiltro(String term);
	
}
