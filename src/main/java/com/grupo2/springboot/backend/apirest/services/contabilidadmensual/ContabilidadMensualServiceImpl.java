package com.grupo2.springboot.backend.apirest.services.contabilidadmensual;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IContabilidadAnualDao;
import com.grupo2.springboot.backend.apirest.dao.IContabilidadMensualDao;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;

@Service
public class ContabilidadMensualServiceImpl implements IContabilidadMensualService {

	@Autowired
	private IContabilidadMensualDao contabilidadMensualDao;

	@Override
	@Transactional
	public ContabilidadMensualVo save(ContabilidadMensualVo contabilidadMensual) {
		return contabilidadMensualDao.save(contabilidadMensual);
	}

	@Override
	@Transactional
	public ContabilidadMensualVo update(ContabilidadMensualVo contabilidadMensual) {
		return contabilidadMensualDao.save(contabilidadMensual);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContabilidadMensualVo> findAll() {
		return (List<ContabilidadMensualVo>) contabilidadMensualDao.findAll();
	}
	


	@Override
	@Transactional(readOnly = true)
	public ContabilidadMensualVo findById(Integer idContabilidadMen) {
		return contabilidadMensualDao.findById(idContabilidadMen).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer findUltima() {
		return contabilidadMensualDao.findUltima();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ContabilidadMensualVo> findAll(Pageable pageable) {
		return contabilidadMensualDao.findAll(pageable);
	}

	@Override
	public List<ContabilidadMensualVo> findByFecha(String term) {
		return contabilidadMensualDao.buscarFecha(term);
	}

}
