package com.grupo2.springboot.backend.apirest.entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="contabilidad_anual")
public class ContabilidadAnualVo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_registro_contabilidad_anual")
	private int idRegistroContabilidadAnual;
	
	@Column(name="ventas_contabilidad_anual")
	private double ventasContabilidadAnual;
	
	@Column(name="egresos_contabilidad_anual")
	private double egresosContabilidadAnual;
	
	@Column(name="ingresos_contabilidad_anual")
	private double ingresosContabilidadAnual;
	
	@Column(name="fecha")
	private Date fecha;

	public int getIdRegistroContabilidadAnual() {
		return idRegistroContabilidadAnual;
	}

	public void setIdRegistroContabilidadAnual(int idRegistroContabilidadAnual) {
		this.idRegistroContabilidadAnual = idRegistroContabilidadAnual;
	}

	public double getVentasContabilidadAnual() {
		return ventasContabilidadAnual;
	}

	public void setVentasContabilidadAnual(double ventasContabilidadAnual) {
		this.ventasContabilidadAnual = ventasContabilidadAnual;
	}

	public double getEgresosContabilidadAnual() {
		return egresosContabilidadAnual;
	}

	public void setEgresosContabilidadAnual(double egresosContabilidadAnual) {
		this.egresosContabilidadAnual = egresosContabilidadAnual;
	}

	public double getIngresosContabilidadAnual() {
		return ingresosContabilidadAnual;
	}

	public void setIngresosContabilidadAnual(double ingresosContabilidadAnual) {
		this.ingresosContabilidadAnual = ingresosContabilidadAnual;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	private static final long serialVersionUID = 1L;
	
}
