package com.grupo2.springboot.backend.apirest.dao;


import java.lang.annotation.Native;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.CarritoClienteVo;

public interface ICarritoClienteDao extends CrudRepository<CarritoClienteVo, Integer>{
	@Query("SELECT idCarrito from CarritoClienteVo WHERE cliente =?1")
	public Integer findIdByCliente(String correoCliente);
	
}
