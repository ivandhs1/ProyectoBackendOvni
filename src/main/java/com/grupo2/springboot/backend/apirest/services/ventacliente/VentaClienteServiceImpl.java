package com.grupo2.springboot.backend.apirest.services.ventacliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.dao.IVentaClienteDao;


@Service
public class VentaClienteServiceImpl implements IVentaClienteService{

	@Autowired
	private IVentaClienteDao ventaClienteDao;
	@Override
	public void eliminarVentaCliente(int idVenta) {
		ventaClienteDao.eliminarVentaCliente(idVenta);
		
	}

}
