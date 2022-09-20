package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "item_carrito")
public class ItemCarritoVo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_puente")
	private int idPuente;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_producto")
	private ProductoVo codigoProducto;
	
	@Column(name = "cantidad_producto")
	private int cantidadProducto;
	
	@Column(name="precio_item")
	private double precioItem;
	
	public int getIdPuente() {
		return idPuente;
	}

	public void setIdPuente(int idPuente) {
		this.idPuente = idPuente;
	}

	public ProductoVo getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(ProductoVo codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public double getPrecioItem() {
		return precioItem;
	}

	public void setPrecioItem() {
		double total;
		total = this.cantidadProducto * this.codigoProducto.getPrecioProducto();
		this.precioItem = total;
	}

	private static final long serialVersionUID = 1L;
}
