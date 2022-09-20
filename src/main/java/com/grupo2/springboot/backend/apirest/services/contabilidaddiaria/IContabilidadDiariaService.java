package com.grupo2.springboot.backend.apirest.services.contabilidaddiaria;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;

public interface IContabilidadDiariaService {

	public ContabilidadDiariaVo save(ContabilidadDiariaVo contabilidadDiaria);
	
	public ContabilidadDiariaVo update(ContabilidadDiariaVo contabilidadDiaria);
	
	public List<ContabilidadDiariaVo> findAll();
	
	public Page<ContabilidadDiariaVo> findAll(Pageable pageable);
	
	public ContabilidadDiariaVo findById(Integer idContabilidadDia);
	
	public Integer findUltima();

	public ContabilidadDiariaVo llenar();
	
	public ContabilidadDiariaVo asignarContabilidadCrearMes(ContabilidadDiariaVo guardada);

	public ContabilidadDiariaVo asignarContabilidadCrearDia(ContabilidadDiariaVo guardada);

	public ContabilidadDiariaVo asignarContabilidadCrearTodo(ContabilidadDiariaVo guardada);
	
	public List<ContabilidadDiariaVo> findByFecha(String term);


}
