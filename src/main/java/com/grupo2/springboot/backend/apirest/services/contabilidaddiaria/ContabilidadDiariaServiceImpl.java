package com.grupo2.springboot.backend.apirest.services.contabilidaddiaria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IContabilidadAnualDao;
import com.grupo2.springboot.backend.apirest.dao.IContabilidadDiariaDao;
import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;
import com.grupo2.springboot.backend.apirest.services.contabilidadanual.IContabilidadAnualService;
import com.grupo2.springboot.backend.apirest.services.contabilidadmensual.IContabilidadMensualService;

@Service
public class ContabilidadDiariaServiceImpl implements IContabilidadDiariaService {

	@Autowired
	private IContabilidadDiariaDao contabilidadDiariaDao;

	@Autowired
	private IContabilidadMensualService contabilidadMensualService;

	@Autowired
	private IContabilidadAnualService contabilidadAnualService;

	@Override
	@Transactional
	public ContabilidadDiariaVo save(ContabilidadDiariaVo contabilidadDiaria) {
		return contabilidadDiariaDao.save(contabilidadDiaria);
	}

	@Override
	@Transactional
	public ContabilidadDiariaVo update(ContabilidadDiariaVo contabilidadDiaria) {
		return contabilidadDiariaDao.save(contabilidadDiaria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContabilidadDiariaVo> findAll() {
		return (List<ContabilidadDiariaVo>) contabilidadDiariaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ContabilidadDiariaVo> findAll(Pageable pageable) {
		return contabilidadDiariaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public ContabilidadDiariaVo findById(Integer idContabilidadDia) {
		return contabilidadDiariaDao.findById(idContabilidadDia).orElse(null);
	}

	@Override
	public Integer findUltima() {
		return contabilidadDiariaDao.findUltima();
	}

	@Override
	public ContabilidadDiariaVo asignarContabilidadCrearMes(ContabilidadDiariaVo guardada) {
		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);
		
		ContabilidadMensualVo contaMensual = new ContabilidadMensualVo();
		contaMensual.setEgresosContabilidadMensual(0);
		contaMensual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaMensual.setIdRegistroContabilidadAnual(contaAnual);
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		ContabilidadDiariaVo contaDiaria = new ContabilidadDiariaVo();
		contaDiaria.setEgresosContabilidadDiaria(0);
		contaDiaria.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaDiaria.setIdRegistroContabilidadMensual(contaMensualGu);
		return this.save(contaDiaria);
		
	}

	@Override
	public ContabilidadDiariaVo asignarContabilidadCrearDia(ContabilidadDiariaVo guardada) {
		Integer contaMensualId = contabilidadMensualService.findUltima();
		ContabilidadMensualVo contaMensual = contabilidadMensualService.findById(contaMensualId);

		ContabilidadDiariaVo contaDiaria = new ContabilidadDiariaVo();
		contaDiaria.setEgresosContabilidadDiaria(0);
		contaDiaria.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaDiaria.setIdRegistroContabilidadMensual(contaMensual);
		return this.save(contaDiaria);

		
	}

	@Override
	public ContabilidadDiariaVo asignarContabilidadCrearTodo(ContabilidadDiariaVo guardada) {
		ContabilidadAnualVo contaAnual = new ContabilidadAnualVo();
		contaAnual.setEgresosContabilidadAnual(0);
		contaAnual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

		ContabilidadMensualVo contaMensual = new ContabilidadMensualVo();
		contaMensual.setEgresosContabilidadMensual(0);
		contaMensual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaMensual.setIdRegistroContabilidadAnual(contaAnualGu);
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		ContabilidadDiariaVo contaDiaria = new ContabilidadDiariaVo();
		contaDiaria.setEgresosContabilidadDiaria(0);
		contaDiaria.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaDiaria.setIdRegistroContabilidadMensual(contaMensualGu);
		return this.save(contaDiaria);
		
		
	}

	@Override
	public ContabilidadDiariaVo llenar() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
		Integer ultimaDId = this.findUltima();
		ContabilidadDiariaVo guardada = null;
		if(ultimaDId==null) {
			ultimaDId = 0;
		}
		ContabilidadDiariaVo ultimaD = this.findById(ultimaDId);
		
		if(ultimaD!=null) {
			if (ultimaD.getFecha().toString().split("-")[0].equals(LocalDate.now().toString().split("-")[0])) {
				if (ultimaD.getFecha().toString().split("-")[1].equals(LocalDate.now().toString().split("-")[1])) {
					
					guardada = this.asignarContabilidadCrearMes(guardada);
				} else {
					guardada = this.asignarContabilidadCrearDia(guardada);
				}
			} else {
				guardada= this.asignarContabilidadCrearTodo(guardada);
			}
		}else {
			guardada=this.asignarContabilidadCrearTodo(guardada);
		}
		
		return guardada;


	}

	@Override
	public List<ContabilidadDiariaVo> findByFecha(String term) {
		return contabilidadDiariaDao.buscarFecha(term);
	}

	
	
	

}
