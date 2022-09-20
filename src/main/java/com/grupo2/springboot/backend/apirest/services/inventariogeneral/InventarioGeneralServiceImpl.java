package com.grupo2.springboot.backend.apirest.services.inventariogeneral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.dao.IInventarioGeneralDao;
import com.grupo2.springboot.backend.apirest.entity.InventarioGeneralVo;

@Service
public class InventarioGeneralServiceImpl implements IinventarioGeneralService{

	@Autowired
	private IInventarioGeneralDao inventarioGeneralDao;
	
	@Override
	public List<InventarioGeneralVo> findAll() {
		
		return (List<InventarioGeneralVo>) inventarioGeneralDao.findAll();
	}

	@Override
	public InventarioGeneralVo findById(Integer id) {

		return inventarioGeneralDao.findById(id).orElse(null);
	}

	@Override
	public InventarioGeneralVo save(InventarioGeneralVo inventarioRegistrar) {

		return inventarioGeneralDao.save(inventarioRegistrar);
	}

	@Override
	public InventarioGeneralVo update(InventarioGeneralVo inventarioMoidficado) {

		return inventarioGeneralDao.save(inventarioMoidficado);
	}

	@Override
	public InventarioGeneralVo findByProducto(Integer producto) {
		
		return inventarioGeneralDao.findByProducto(producto);
	}

	@Override
	public List<InventarioGeneralVo> findAllorden() {
		return inventarioGeneralDao.findByCantidad();
	}

	@Override
	public List<InventarioGeneralVo> findAllPositvos() {
		return inventarioGeneralDao.findByCantidadPositiva();
	}

	@Override
	public List<InventarioGeneralVo> findAllPositvosFiltrado(String term) {

		return inventarioGeneralDao.findByCantidadPositivaFiltrado(term);
	}

	public List<Integer> findDestacado() {
		// TODO Auto-generated method stub
		return inventarioGeneralDao.findDestacado();
	}

	public List<InventarioGeneralVo> findByCantidadPositivaFiltradoNombre(String term) {
		return inventarioGeneralDao.findByCantidadPositivaFiltradoNombre(term);
	}

}
