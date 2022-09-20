package com.grupo2.springboot.backend.apirest.services.carritocliente;


import com.grupo2.springboot.backend.apirest.entity.CarritoClienteVo;

public interface ICarritoClienteService {
	
	public CarritoClienteVo findById(Integer id);
	
	public Integer findIdByCliente(String correoCliente);
	
	public CarritoClienteVo save(CarritoClienteVo carrito);
}
