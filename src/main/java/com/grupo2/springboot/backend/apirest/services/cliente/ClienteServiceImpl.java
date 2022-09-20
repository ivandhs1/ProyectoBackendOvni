package com.grupo2.springboot.backend.apirest.services.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IClienteDao;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<ClienteVo> findAll() {
		return (List<ClienteVo>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ClienteVo findById(String correoId) {
		return clienteDao.findById(correoId).orElse(null);
	}

	@Override
	@Transactional
	public ClienteVo findByNombre(String nombre) {
		return clienteDao.findByNombre(nombre);
	}

	@Override
	@Transactional
	public ClienteVo save(ClienteVo cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public ClienteVo update(ClienteVo cliente) {
		return clienteDao.save(cliente);
	}

}
