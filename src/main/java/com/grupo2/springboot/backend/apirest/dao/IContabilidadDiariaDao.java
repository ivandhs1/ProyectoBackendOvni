package com.grupo2.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;

public interface IContabilidadDiariaDao extends JpaRepository<ContabilidadDiariaVo, Integer> {

	@Query("SELECT MAX(idRegistroContabilidadDiaria) FROM ContabilidadDiariaVo")
	public Integer findUltima();

	@Query(value="SELECT * FROM contabilidad_diaria WHERE fecha like %?1%" , nativeQuery = true)
	public List<ContabilidadDiariaVo> buscarFecha(String term);
}
