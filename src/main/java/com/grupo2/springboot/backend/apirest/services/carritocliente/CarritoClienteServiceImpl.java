package com.grupo2.springboot.backend.apirest.services.carritocliente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.ICarritoClienteDao;
import com.grupo2.springboot.backend.apirest.entity.CarritoClienteVo;

@Service
public class CarritoClienteServiceImpl implements ICarritoClienteService{
	
	@Autowired
	private ICarritoClienteDao carritoDao;

	@Override
	@Transactional(readOnly = true)
	public Integer findIdByCliente(String correoCliente) {
		return carritoDao.findIdByCliente(correoCliente);
	}

	@Override
	@Transactional(readOnly = true)
	public CarritoClienteVo findById(Integer id) {
		return carritoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public CarritoClienteVo save(CarritoClienteVo carrito) {
		System.out.println("2222222222");
		return carritoDao.save(carrito);
	}

}
