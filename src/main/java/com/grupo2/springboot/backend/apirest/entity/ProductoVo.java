package com.grupo2.springboot.backend.apirest.entity;

import javax.persistence.*;

@Entity
@Table(name="producto")
public class ProductoVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_producto")
	private int codigoProducto;
	
	@Column(name="nombre_producto")
	private String nombreProducto;
	
	@Column(name="precio_producto")
	private double precioProducto;
	
	@Column(name="precio_producto_proveedor")
	private double precioProductoProveedor;
	
	@Column(name="descripcion_producto")
	private String descripcionProducto;
	
	@Column(name="foto_producto")
	private String fotoProducto;
	
	@Column(name="estado")
	private String estado;

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

	public double getPrecioProductoProveedor() {
		return precioProductoProveedor;
	}

	public void setPrecioProductoProveedor(double precioProductoProveedor) {
		this.precioProductoProveedor = precioProductoProveedor;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripciProducto) {
		this.descripcionProducto = descripciProducto;
	}

	public String getFotoProducto() {
		return fotoProducto;
	}

	public void setFotoProducto(String fotoProducto) {
		this.fotoProducto = fotoProducto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	private static final long serialVersionUID = 1L;
	
}
