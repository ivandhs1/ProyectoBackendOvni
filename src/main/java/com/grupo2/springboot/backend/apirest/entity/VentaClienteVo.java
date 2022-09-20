package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "venta_cliente")
public class VentaClienteVo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_puente")
	private int idPuente;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_producto")
	private ProductoVo codigoProducto;

	@Column(name = "cantidad_producto")
	private int cantidadProducto;
	
	@Column(name = "precio_venta_detalle")
	private double precioVentaDetalle;

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

	public double getPrecioVentaDetalle() {
		return precioVentaDetalle;
	}

	public void setPrecioVentaDetalle() {
		double total;
		total = this.cantidadProducto * this.codigoProducto.getPrecioProducto();
		this.precioVentaDetalle = total;
	}

	private static final long serialVersionUID = 1L;

}
