package com.grupo2.springboot.backend.apirest.services.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.dao.IPedidoDao;
import com.grupo2.springboot.backend.apirest.entity.PedidoVo;

@Service
public class PedidoServiceImpl implements IPedidoService {
	
	@Autowired
	private IPedidoDao pedidoDao;

	@Override
	public PedidoVo create(PedidoVo pedido) {
		return pedidoDao.save(pedido);
	}

	@Override
	public PedidoVo update(PedidoVo pedido) {
		return pedidoDao.save(pedido);
	}

	@Override
	public List<PedidoVo> findByAll() {
		return (List<PedidoVo>) pedidoDao.findAll();
	}

	@Override
	public List<PedidoVo> findByPendientes() {
		return pedidoDao.findByEstadoPendiente();
	}

	@Override
	public List<PedidoVo> findByProceso() {
		return pedidoDao.findByEstadoProceso();
	}

	@Override
	public List<PedidoVo> findByCompleto() {
		return pedidoDao.findByEstadoCompletado();
	}

	@Override
	public List<PedidoVo> findByCancelado() {
		return pedidoDao.findByEstadoCancelado();
	}
	
	@Override
	public List<PedidoVo> findByCliente(String term) {
		return pedidoDao.findByCliente(term);
	}

	@Override
	public List<PedidoVo> findByClienteEspecifico(String term) {
		return pedidoDao.findByClienteEspecifico(term);
	}

}
