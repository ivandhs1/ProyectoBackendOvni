package com.grupo2.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.PedidoVo;

public interface IPedidoDao extends CrudRepository<PedidoVo,Integer>{
	
	@Query("select p from PedidoVo p where p.estado = 1")
	public List<PedidoVo> findByEstadoPendiente();
	
	@Query("select p from PedidoVo p where p.estado = 2")
	public List<PedidoVo> findByEstadoProceso();
	
	@Query("select p from PedidoVo p where p.estado = 3")
	public List<PedidoVo> findByEstadoCompletado();
	
	@Query("select p from PedidoVo p where p.estado = 4")
	public List<PedidoVo> findByEstadoCancelado();
	
	@Query("select p from PedidoVo p where p.cliente.correoCliente = ?1")
	public List<PedidoVo> findByCliente(String term);
	
	@Query("select p from PedidoVo p where p.cliente.correoCliente = ?1 and p.estado = 1 or p.estado = 2")
	public List<PedidoVo> findByClienteEspecifico(String term);
}
