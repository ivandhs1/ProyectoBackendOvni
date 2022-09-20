package com.grupo2.springboot.backend.apirest.services.venta;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;

public interface IVentaService {

	public List<VentaVo> findAll();
	
	public VentaVo save(VentaVo venta);
	
	public VentaVo findById(Integer idVenta);
	
	public void gestorAsignarContabilidad(VentaVo ventaNew, VentaVo venta);
	
	public void asignarContabilidadDiaHoy(ContabilidadDiariaVo ultimaD, VentaVo ventaNew, VentaVo venta);
	
	public void asignarContabilidadMesMalo(VentaVo ventaNew, VentaVo venta);
	
	public void asignarContabilidadDiaMalo(VentaVo ventaNew, VentaVo venta);
	
	public void asignarContabilidadCrearTodo(VentaVo ventaNew, VentaVo venta);
	
	public void eliminarVenta(int idVenta);
}
