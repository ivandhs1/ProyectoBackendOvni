package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="inventario_detalles")
public class InventarioDetallesVo implements Serializable{
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalles")
	private int idDetalles;
	
	@Column(name="cantidad_producto")
	private int cantidadProducto;
	
	@Column(name="fecha_ultimo_ingreso_inventario")
	private Date fechaUltimoIngresoInventario;	
	
	public int getIdDetalles() {
		return idDetalles;
	}

	public void setIdDetalles(int idDetalles) {
		this.idDetalles = idDetalles;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public Date getFechaUltimoIngresoInventario() {
		return fechaUltimoIngresoInventario;
	}

	public void setFechaUltimoIngresoInventario(Date fechaUltimoIngresoInventario) {
		this.fechaUltimoIngresoInventario = fechaUltimoIngresoInventario;
	}
	
	private static final long serialVersionUID = 1L;
	
}
