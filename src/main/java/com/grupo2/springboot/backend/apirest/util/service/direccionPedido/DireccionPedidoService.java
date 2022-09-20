package com.grupo2.springboot.backend.apirest.util.service.direccionPedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.dao.IDireccionPedido;
import com.grupo2.springboot.backend.apirest.entity.DireccionPedidoVo;

@Service
public class DireccionPedidoService implements IDireccionPedidoService{
	
	@Autowired
	private IDireccionPedido direccionPedidoDao;

	@Override
	public DireccionPedidoVo save(DireccionPedidoVo direccionPedido) {
		return direccionPedidoDao.save(direccionPedido);
	}

}
