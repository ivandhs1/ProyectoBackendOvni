package com.grupo2.springboot.backend.apirest.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="contabilidad_mensual")
public class ContabilidadMensualVo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_registro_contabilidad_mensual")
	private int idRegistroContabilidadMensual;
	
	@Column(name="ventas_contabilidad_mensual")
	private double ventasContabilidadMensual;
	
	@Column(name="egresos_contabilidad_mensual")
	private double egresosContabilidadMensual;
	
	@Column(name="ingresos_contabilidad_mensual")
	private double ingresosContabilidadMensual;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_registro_contabilidad_anual")
	private ContabilidadAnualVo idRegistroContabilidadAnual;
	
	@Column(name="fecha")
	private Date fecha;

	public int getIdRegistroContabilidadMensual() {
		return idRegistroContabilidadMensual;
	}

	public void setIdRegistroContabilidadMensual(int idRegistroContabilidadMensual) {
		this.idRegistroContabilidadMensual = idRegistroContabilidadMensual;
	}

	public double getVentasContabilidadMensual() {
		return ventasContabilidadMensual;
	}

	public void setVentasContabilidadMensual(double ventasContabilidadMensual) {
		this.ventasContabilidadMensual = ventasContabilidadMensual;
	}

	public double getEgresosContabilidadMensual() {
		return egresosContabilidadMensual;
	}

	public void setEgresosContabilidadMensual(double egresosContabilidadMensual) {
		this.egresosContabilidadMensual = egresosContabilidadMensual;
	}

	public double getIngresosContabilidadMensual() {
		return ingresosContabilidadMensual;
	}

	public void setIngresosContabilidadMensual(double ingresosContabilidadMensual) {
		this.ingresosContabilidadMensual = ingresosContabilidadMensual;
	}

	public ContabilidadAnualVo getIdRegistroContabilidadAnual() {
		return idRegistroContabilidadAnual;
	}

	public void setIdRegistroContabilidadAnual(ContabilidadAnualVo idRegistroContabilidadAnual) {
		this.idRegistroContabilidadAnual = idRegistroContabilidadAnual;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	private static final long serialVersionUID = 1L;

}
