package com.grupo2.springboot.backend.apirest.services.inventariogeneral;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.InventarioGeneralVo;

public interface IinventarioGeneralService {
	
	public List<InventarioGeneralVo> findAll();
	
	public InventarioGeneralVo findById(Integer id);
	
	public InventarioGeneralVo save(InventarioGeneralVo inventarioRegistrar);
	
	public InventarioGeneralVo update(InventarioGeneralVo inventarioMoidficado);
	
	public InventarioGeneralVo findByProducto(Integer producto);
	
	public List<InventarioGeneralVo> findAllorden();
	
	public List<InventarioGeneralVo> findAllPositvos();
	
	public List<InventarioGeneralVo> findAllPositvosFiltrado(String term);
	
	public List<Integer> findDestacado();

	public List<InventarioGeneralVo> findByCantidadPositivaFiltradoNombre(String term);

}
