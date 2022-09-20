package com.grupo2.springboot.backend.apirest.services.inventariodetalles;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioDetallesVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;

public interface IinventarioDetallesService {
	
	public List<InventarioDetallesVo> findAll();
	
	public InventarioDetallesVo findById(Integer id);
	
	public InventarioDetallesVo save(InventarioDetallesVo inventarioRegistrar);
	
	public InventarioDetallesVo update(InventarioDetallesVo inventarioMoidficado);
	
	public void InsertarInventario(CompraVo compra);
	
	public EstadoProducto disminuirCantidad(VentaVo venta);
	
	public void ventaDevuelta(VentaVo venta);

}
