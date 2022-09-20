package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "compra_admin")
public class CompraAdminVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id_puente")
	private int idPuente;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_producto")
	private ProductoVo codigoProducto;

	@Column(name = "cantidad_producto")
	private int cantidadProducto;
	
	@Column(name = "precio_compra_detalle")
	private double precioCompraDetalle;

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

	public double getPrecioCompraDetalle() {
		return precioCompraDetalle;
	}

	public void setPrecioCompraDetalle() {
		double total = this.getCodigoProducto().getPrecioProductoProveedor()*this.cantidadProducto;
		this.precioCompraDetalle = total;
	}

}
