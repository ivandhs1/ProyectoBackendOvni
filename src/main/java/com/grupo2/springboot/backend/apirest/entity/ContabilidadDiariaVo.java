package com.grupo2.springboot.backend.apirest.entity;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="contabilidad_diaria")
public class ContabilidadDiariaVo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_registro_contabilidad_diaria")
	private int idRegistroContabilidadDiaria;
	
	@Column(name="ventas_contabilidad_diaria")
	private double ventasContabilidadDiaria;
	
	@Column(name="egresos_contabilidad_diaria")
	private double egresosContabilidadDiaria;
	
	@Column(name="ingresos_contabilidad_diaria")
	private double ingresosContabilidadDiaria;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_registro_contabilidad_mensual")
	private ContabilidadMensualVo idRegistroContabilidadMensual;
	
	@Column(name="fecha")
	private Date fecha;
		
	public int getIdRegistroContabilidadDiaria() {
		return idRegistroContabilidadDiaria;
	}

	public void setIdRegistroContabilidadDiaria(int idRegistroContabilidadDiaria) {
		this.idRegistroContabilidadDiaria = idRegistroContabilidadDiaria;
	}

	public double getVentasContabilidadDiaria() {
		return ventasContabilidadDiaria;
	}

	public void setVentasContabilidadDiaria(double ventasContabilidadDiaria) {
		this.ventasContabilidadDiaria = ventasContabilidadDiaria;
	}

	public double getEgresosContabilidadDiaria() {
		return egresosContabilidadDiaria;
	}

	public void setEgresosContabilidadDiaria(double egresosContabilidadDiaria) {
		this.egresosContabilidadDiaria = egresosContabilidadDiaria;
	}

	public double getIngresosContabilidadDiaria() {
		return ingresosContabilidadDiaria;
	}

	public void setIngresosContabilidadDiaria(double ingresosContabilidadDiaria) {
		this.ingresosContabilidadDiaria = ingresosContabilidadDiaria;
	}

	public ContabilidadMensualVo getIdRegistroContabilidadMensual() {
		return idRegistroContabilidadMensual;
	}

	public void setIdRegistroContabilidadMensual(ContabilidadMensualVo idRegistroContabilidadMensual) {
		this.idRegistroContabilidadMensual = idRegistroContabilidadMensual;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	private static final long serialVersionUID = 1L;
}
