package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;

public interface IClienteDao extends CrudRepository<ClienteVo, String>{
	
	
	@Query("select cliente from ClienteVo cliente where cliente.nombreCliente like %?1%")
	public ClienteVo findByNombre(String term);
	
}
