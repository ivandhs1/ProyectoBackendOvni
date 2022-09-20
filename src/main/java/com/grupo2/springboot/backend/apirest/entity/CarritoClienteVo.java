package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="carrito_cliente")
public class CarritoClienteVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_carrito")
	private int idCarrito;
	
	@Column(name="precio_carrito")
	private double precioCarrito;
	
	@Column(name="cantidad_carrito")
	private int cantidadCarrito;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnoreProperties(value={"carrito","hibernateLazyInitializer","handler"},allowSetters=true)
	@JoinColumn(name="cliente")
	private ClienteVo cliente;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "carrito")
	private List<ItemCarritoVo> itemCarrito;

	public int getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public double getPrecioCarrito() {
		return precioCarrito;
	}

	public void setPrecioCarrito(double precioCarrito) {
		this.precioCarrito = precioCarrito;
	}

	public int getCantidadCarrito() {
		return cantidadCarrito;
	}

	public void setCantidadCarrito(int cantidadCarrito) {
		this.cantidadCarrito = cantidadCarrito;
	}

	public ClienteVo getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVo cliente) {
		this.cliente = cliente;
	}

	public List<ItemCarritoVo> getItemCarrito() {
		return itemCarrito;
	}

	public void setItemCarrito(List<ItemCarritoVo> itemCarrito) {
		this.itemCarrito = itemCarrito;
	}
	
}
