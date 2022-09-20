package com.grupo2.springboot.backend.apirest.services.contabilidadmensual;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;

public interface IContabilidadMensualService {

	public ContabilidadMensualVo save(ContabilidadMensualVo contabilidadMensual);
	
	public ContabilidadMensualVo update(ContabilidadMensualVo contabilidadMensual);
	
	public List<ContabilidadMensualVo> findAll();
	
	public Page<ContabilidadMensualVo> findAll(Pageable pageable);
	
	public ContabilidadMensualVo findById(Integer idContabilidadAn);
	
	public Integer findUltima();
	
	public List<ContabilidadMensualVo> findByFecha(String term);
}
