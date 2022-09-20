package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.InventarioDetallesVo;

public interface IInventarioDetallesDao extends CrudRepository<InventarioDetallesVo, Integer>{
	
	@Modifying
	@Query("delete from InventarioDetallesVo inventario where inventario.cantidadProducto = ?1")
	public void deleteCantida(Integer cantidad);
}
