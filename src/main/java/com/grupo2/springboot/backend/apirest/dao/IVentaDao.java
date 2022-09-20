package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;

public interface IVentaDao extends CrudRepository<VentaVo, Integer>{

	@Transactional
	@Modifying
	@Query(value="DELETE from venta where codigo_venta = ?1", nativeQuery=true)
	public void eliminarVenta(int idVenta);
}
