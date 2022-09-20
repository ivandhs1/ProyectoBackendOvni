package com.grupo2.springboot.backend.apirest.services.pedido;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.PedidoVo;

public interface IPedidoService {
	
	public PedidoVo create(PedidoVo pedido);
	
	public PedidoVo update(PedidoVo pedido);
	
	public List<PedidoVo> findByAll();
	
	public List<PedidoVo> findByPendientes();
	
	public List<PedidoVo> findByProceso();
	
	public List<PedidoVo> findByCompleto();
	
	public List<PedidoVo> findByCancelado();
	
	public List<PedidoVo> findByCliente(String term);
	
	public List<PedidoVo> findByClienteEspecifico(String term);

}
