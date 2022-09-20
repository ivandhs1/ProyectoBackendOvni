package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import com.grupo2.springboot.backend.apirest.entity.DireccionPedidoVo;

public interface IDireccionPedido extends CrudRepository<DireccionPedidoVo, Integer>{

}
