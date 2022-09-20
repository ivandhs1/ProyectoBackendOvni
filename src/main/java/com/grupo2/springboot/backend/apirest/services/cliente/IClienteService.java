package com.grupo2.springboot.backend.apirest.services.cliente;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;

public interface IClienteService {

	
	public List<ClienteVo> findAll();
	public ClienteVo findById(String correoId);
	public ClienteVo findByNombre(String nombre);
	public ClienteVo save(ClienteVo cliente);
	public ClienteVo update(ClienteVo cliente);
}
