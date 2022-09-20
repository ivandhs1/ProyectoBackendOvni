package com.grupo2.springboot.backend.apirest.services.inventariodetalles;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IInventarioDetallesDao;
import com.grupo2.springboot.backend.apirest.dao.IInventarioGeneralDao;
import com.grupo2.springboot.backend.apirest.entity.CompraAdminVo;
import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioDetallesVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioGeneralVo;
import com.grupo2.springboot.backend.apirest.entity.ProductoVo;
import com.grupo2.springboot.backend.apirest.entity.VentaClienteVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;

@Service
public class InventarioDetallesServiceImpl implements IinventarioDetallesService{
	
	@Autowired
	private IInventarioDetallesDao inventarioDetallesDao;
	
	@Autowired
	private IInventarioGeneralDao inventarioGeneralDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<InventarioDetallesVo> findAll() {
		
		return (List<InventarioDetallesVo>) inventarioDetallesDao.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public InventarioDetallesVo findById(Integer id) {
		
		return inventarioDetallesDao.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public InventarioDetallesVo save(InventarioDetallesVo inventarioRegistrar) {
		
		return inventarioDetallesDao.save(inventarioRegistrar);
	}
	
	@Transactional
	@Override
	public InventarioDetallesVo update(InventarioDetallesVo inventarioMoidficado) {
		
		return inventarioDetallesDao.save(inventarioMoidficado);
	}
	
	@Transactional
	@Override
	public void InsertarInventario(CompraVo compra) {
		
		List<CompraAdminVo> listaCompras = compra.getCompras();
		
		InventarioGeneralVo nuevoInventarioGeneral = null;
		
		
		//List<InventarioGeneralVo> inventarios = new ArrayList<>();
		
		for (CompraAdminVo compraAdminVo : listaCompras) {
			
			List<InventarioDetallesVo> detallesAnteriores = new ArrayList<>();
			
			ProductoVo producto = compraAdminVo.getCodigoProducto();
			int cantidad = compraAdminVo.getCantidadProducto();
			LocalDate fecha = LocalDate.now();
			InventarioDetallesVo nuevoInventarioDetalle = new InventarioDetallesVo();
			
			nuevoInventarioDetalle.setCantidadProducto(cantidad);
			nuevoInventarioDetalle.setFechaUltimoIngresoInventario(Date.valueOf(fecha));
			
			
			nuevoInventarioGeneral = inventarioGeneralDao.findByProducto(producto.getCodigoProducto());
			

			if(nuevoInventarioGeneral != null) {
				detallesAnteriores = nuevoInventarioGeneral.getDetalles();
			}else {
				nuevoInventarioGeneral = new InventarioGeneralVo();
				nuevoInventarioGeneral.setCodigoProducto(producto);
			}
			
			 
			detallesAnteriores.add(nuevoInventarioDetalle);
			nuevoInventarioGeneral.setDetalles(detallesAnteriores);
			
			nuevoInventarioGeneral.setCantidadProducto();
			
			//inventarios.add(nuevoInventarioGeneral);
			
			
			inventarioGeneralDao.save(nuevoInventarioGeneral);
			
		}
		/*
		for(InventarioGeneralVo inventario : inventarios) {
			System.out.println(inventario);
			inventarioGeneralDao.save(inventario);
		}
		*/
	}
	
	@Transactional
	@Override
	public void ventaDevuelta(VentaVo venta) {
		
		List<VentaClienteVo> listaItems = venta.getVentas();
		
		InventarioGeneralVo InventarioGeneral = null;
		
		
		//List<InventarioGeneralVo> inventarios = new ArrayList<>();
		
		for (VentaClienteVo detalle : listaItems) {
			
			List<InventarioDetallesVo> detallesAnteriores = new ArrayList<>();
			
			ProductoVo producto = detalle.getCodigoProducto();
			int cantidad = detalle.getCantidadProducto();
			LocalDate fecha = LocalDate.now();
			InventarioDetallesVo nuevoInventarioDetalle = new InventarioDetallesVo();
			
			nuevoInventarioDetalle.setCantidadProducto(cantidad);
			nuevoInventarioDetalle.setFechaUltimoIngresoInventario(Date.valueOf(fecha));
			
			
			InventarioGeneral = inventarioGeneralDao.findByProducto(producto.getCodigoProducto());
			

		
			detallesAnteriores = InventarioGeneral.getDetalles();
			
			
			 
			detallesAnteriores.add(nuevoInventarioDetalle);
			InventarioGeneral.setDetalles(detallesAnteriores);
			
			InventarioGeneral.setCantidadProducto();
			
			//inventarios.add(nuevoInventarioGeneral);
			
			
			inventarioGeneralDao.save(InventarioGeneral);
			
		}
		/*
		for(InventarioGeneralVo inventario : inventarios) {
			System.out.println(inventario);
			inventarioGeneralDao.save(inventario);
		}
		*/
	}
	
	@Transactional(readOnly=true)
	public EstadoProducto validacionCantidad(List<VentaClienteVo> detallesVenta) {
		
		List<EstadoProductoIndividual> estadoProductoIndividual = new ArrayList<>();
		
		for(VentaClienteVo venta : detallesVenta) {
			int codigoProducto = venta.getCodigoProducto().getCodigoProducto();
			
			InventarioGeneralVo comprovarInventario = inventarioGeneralDao.findByProducto(codigoProducto);
			String nombre = venta.getCodigoProducto().getNombreProducto();
			int cantidad = venta.getCantidadProducto();
			EstadoProductoIndividual estadoProductoI = new EstadoProductoIndividual(nombre, true,comprovarInventario.getCantidadProducto());
			if(cantidad>comprovarInventario.getCantidadProducto()) {
				
				estadoProductoI.setEstado(false);
			}
			estadoProductoIndividual.add(estadoProductoI);
		}
		EstadoProducto estadoProductos = new EstadoProducto(true,estadoProductoIndividual);
		estadoProductos.setEstado();
		return estadoProductos;
	}
	
	@Override
	@Transactional
	public EstadoProducto disminuirCantidad(VentaVo venta) {
		
		EstadoProducto estadoProductos = this.validacionCantidad(venta.getVentas());
		
		
		if(estadoProductos.isEstado()==true) {
			for(VentaClienteVo detallesVenta: venta.getVentas()) {
				int codigoProducto = detallesVenta.getCodigoProducto().getCodigoProducto();
				InventarioGeneralVo actualizarInventarioG = inventarioGeneralDao.findByProducto(codigoProducto);
				
				List<InventarioDetallesVo> actualizarDetalles = actualizarInventarioG.getDetalles();
				
				int cantidadVenta = detallesVenta.getCantidadProducto();
				
				System.out.println(cantidadVenta + actualizarDetalles.size());
				
				System.out.println(cantidadVenta + actualizarDetalles.size());
				
				for(InventarioDetallesVo detalle : actualizarDetalles) {
					int cantidadInventario = detalle.getCantidadProducto();
					if(cantidadInventario>=cantidadVenta) {
						cantidadInventario -= cantidadVenta;
						if(cantidadInventario==0) {
							detalle.setCantidadProducto(0);
							break;
						}else {
							detalle.setCantidadProducto(cantidadInventario);
							break;
						}
					}else {
						cantidadVenta -= cantidadInventario;
						detalle.setCantidadProducto(0);
					}
				}
				actualizarInventarioG.setCantidadProducto();
				inventarioGeneralDao.save(actualizarInventarioG);
			}
		}
		inventarioDetallesDao.deleteCantida(0);
		return estadoProductos;
	}

}
