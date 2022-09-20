package com.grupo2.springboot.backend.apirest.services.compra;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;

public interface ICompraService {
	
	public List<CompraVo> findAll();
	public CompraVo findById(Integer id);
	public CompraVo save(CompraVo compra);

	public void gestorAsignarContabilidad(CompraVo compraNew, CompraVo compra);
	
	public void asignarContabilidadDiaHoy(ContabilidadDiariaVo ultimaD, CompraVo compraNew, CompraVo compra);
	
	public void asignarContabilidadMesMalo(CompraVo compraNew, CompraVo compra);
	
	public void asignarContabilidadDiaMalo(CompraVo compraNew, CompraVo compra);
	
	public void asignarContabilidadCrearTodo(CompraVo compraNew, CompraVo compra);
}
