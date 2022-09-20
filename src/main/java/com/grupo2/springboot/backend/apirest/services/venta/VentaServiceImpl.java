package com.grupo2.springboot.backend.apirest.services.venta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.grupo2.springboot.backend.apirest.services.contabilidadanual.IContabilidadAnualService;
import com.grupo2.springboot.backend.apirest.services.contabilidaddiaria.IContabilidadDiariaService;
import com.grupo2.springboot.backend.apirest.services.contabilidadmensual.IContabilidadMensualService;
import com.grupo2.springboot.backend.apirest.services.producto.IProductoService;
import com.grupo2.springboot.backend.apirest.dao.IVentaDao;
@Service
public class VentaServiceImpl implements IVentaService{

	@Autowired
	private IVentaDao ventaDao;
	
	@Autowired
	private IContabilidadDiariaService contabilidadDiariaService;

	@Autowired
	private IContabilidadMensualService contabilidadMensualService;

	@Autowired
	private IContabilidadAnualService contabilidadAnualService;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<VentaVo> findAll() {
		return (List<VentaVo>) ventaDao.findAll();
	}

	@Override
	@Transactional
	public VentaVo save(VentaVo venta) {
		return ventaDao.save(venta);
	}

	@Override
	public VentaVo findById(Integer idVenta) {
		return ventaDao.findById(idVenta).orElse(null);
	}

	@Override
	public void asignarContabilidadDiaHoy(ContabilidadDiariaVo ultimaD, VentaVo ventaNew, VentaVo venta) {
		
		Integer contaMensualId = contabilidadMensualService.findUltima();
		ContabilidadMensualVo contaMensual = contabilidadMensualService.findById(contaMensualId);
		

		contaMensual.setIngresosContabilidadMensual(contaMensual.getIngresosContabilidadMensual() + venta.getPrecioVenta());
		contaMensual.setVentasContabilidadMensual(contaMensual.getVentasContabilidadMensual() + 1);
		contabilidadMensualService.save(contaMensual);

		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

		contaAnual.setIngresosContabilidadAnual(contaAnual.getIngresosContabilidadAnual() + venta.getPrecioVenta());
		contaAnual.setVentasContabilidadAnual(contaAnual.getVentasContabilidadAnual() + 1);
		contabilidadAnualService.save(contaAnual);

		ultimaD.setVentasContabilidadDiaria(ultimaD.getVentasContabilidadDiaria() + 1);
		ultimaD.setIngresosContabilidadDiaria(ultimaD.getIngresosContabilidadDiaria() + venta.getPrecioVenta());
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(ultimaD);
		
		
		ventaNew.setIdRegistroContabilidadDiaria(contaDiaGu);
	}

	@Override
	public void asignarContabilidadMesMalo(VentaVo ventaNew, VentaVo venta) {
		Integer contaMensualId = contabilidadMensualService.findUltima();
		ContabilidadMensualVo contaMensual = contabilidadMensualService.findById(contaMensualId);

		contaMensual.setIngresosContabilidadMensual(contaMensual.getIngresosContabilidadMensual() + venta.getPrecioVenta());
		contaMensual.setVentasContabilidadMensual(contaMensual.getVentasContabilidadMensual() + 1);
		contabilidadMensualService.save(contaMensual);

		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

		contaAnual.setIngresosContabilidadAnual(contaAnual.getIngresosContabilidadAnual() + venta.getPrecioVenta());
		contaAnual.setVentasContabilidadAnual(contaAnual.getVentasContabilidadAnual() + 1);
		contabilidadAnualService.save(contaAnual);

		ContabilidadDiariaVo contaHoy = new ContabilidadDiariaVo();
		contaHoy.setIngresosContabilidadDiaria(venta.getPrecioVenta());
		contaHoy.setVentasContabilidadDiaria(1);
		contaHoy.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaHoy.setIdRegistroContabilidadMensual(contaMensual);
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaHoy);
		
		ventaNew.setIdRegistroContabilidadDiaria(contaDiaGu);
	}

	@Override
	public void asignarContabilidadDiaMalo(VentaVo ventaNew, VentaVo venta) {
		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

		contaAnual.setIngresosContabilidadAnual(contaAnual.getIngresosContabilidadAnual() + venta.getPrecioVenta());
		contaAnual.setVentasContabilidadAnual(contaAnual.getVentasContabilidadAnual() + 1);
		ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

		ContabilidadMensualVo contaMensual = new ContabilidadMensualVo();
		contaMensual.setIngresosContabilidadMensual(venta.getPrecioVenta());
		contaMensual.setVentasContabilidadMensual(1);
		contaMensual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaMensual.setIdRegistroContabilidadAnual(contaAnualGu);
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		ContabilidadDiariaVo contaDiaria = new ContabilidadDiariaVo();
		contaDiaria.setIngresosContabilidadDiaria(venta.getPrecioVenta());
		contaDiaria.setVentasContabilidadDiaria(1);
		contaDiaria.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaDiaria.setIdRegistroContabilidadMensual(contaMensualGu);
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaDiaria);
		
		ventaNew.setIdRegistroContabilidadDiaria(contaDiaGu);
	}

	@Override
	public void asignarContabilidadCrearTodo(VentaVo ventaNew, VentaVo venta) {
		ContabilidadAnualVo contaAnual = new ContabilidadAnualVo();
		contaAnual.setIngresosContabilidadAnual(venta.getPrecioVenta());
		contaAnual.setVentasContabilidadAnual(1);
		contaAnual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

		ContabilidadMensualVo contaMensual = new ContabilidadMensualVo();
		contaMensual.setIngresosContabilidadMensual(venta.getPrecioVenta());
		contaMensual.setVentasContabilidadMensual(1);
		contaMensual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaMensual.setIdRegistroContabilidadAnual(contaAnualGu);
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		ContabilidadDiariaVo contaDiaria = new ContabilidadDiariaVo();
		contaDiaria.setIngresosContabilidadDiaria(venta.getPrecioVenta());
		contaDiaria.setVentasContabilidadDiaria(1);
		contaDiaria.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaDiaria.setIdRegistroContabilidadMensual(contaMensualGu);
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaDiaria);
		
		ventaNew.setIdRegistroContabilidadDiaria(contaDiaGu);
	}

	@Override
	public void gestorAsignarContabilidad(VentaVo ventaNew, VentaVo venta) {
		
		Integer ultimaDId = contabilidadDiariaService.findUltima();
		if(ultimaDId==null) {
			ultimaDId = 0;
		}
		
		ContabilidadDiariaVo ultimaD = contabilidadDiariaService.findById(ultimaDId);
		if (ultimaD.getFecha().toString().split("-")[0].equals(ventaNew.getFechaVenta().toString().split("-")[0]) && ultimaD.getFecha().toString().split("-")[1].equals(ventaNew.getFechaVenta().toString().split("-")[1]) && ultimaD.getFecha().toString().split("-")[2].equals(ventaNew.getFechaVenta().toString().split("-")[2].split("T")[0])) {
			
			this.asignarContabilidadDiaHoy(ultimaD, ventaNew, venta);
		} else {
			if (ventaNew.getFechaVenta().toString().split("-")[0].equals(ultimaD.getFecha().toString().split("-")[0])) {
				if (ventaNew.getFechaVenta().toString().split("-")[1].equals(ultimaD.getFecha().toString().split("-")[1])) {
					this.asignarContabilidadMesMalo(ventaNew, venta);
					
				} else {
					this.asignarContabilidadDiaMalo(ventaNew,venta);
				}
			} else {
				this.asignarContabilidadCrearTodo(ventaNew,venta);
			}
		}
	}

	@Override
	public void eliminarVenta(int id) {
		ventaDao.eliminarVenta(id);
	}


}
