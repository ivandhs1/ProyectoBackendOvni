package com.grupo2.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;


public interface IContabilidadAnualDao extends JpaRepository<ContabilidadAnualVo, Integer> {

	@Query("SELECT MAX(idRegistroContabilidadAnual) FROM ContabilidadAnualVo")
	public Integer findUltima();
	
	@Query(value="SELECT * FROM contabilidad_anual WHERE fecha like %?1%" , nativeQuery = true)
	public List<ContabilidadAnualVo> buscarFecha(String term);
}
