package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.VentaClienteVo;

public interface IVentaClienteDao extends CrudRepository<VentaClienteVo, Integer>{
	
	@Transactional
	@Modifying
	@Query(value="DELETE from venta_cliente where venta = ?1", nativeQuery=true)
	public void eliminarVentaCliente(int idVenta);
}
