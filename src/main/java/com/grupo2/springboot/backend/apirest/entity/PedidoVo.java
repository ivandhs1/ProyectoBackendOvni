package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="pedido")
public class PedidoVo implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String estado;
	
	@Column(name = "modo")
	private String modoAdquirir;
	
	@JsonIgnoreProperties(value = {"pedidos","user","comprasAdmin", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrador")
	private AdministradorVo administrador;
	
	@JsonIgnoreProperties(value={ "user","carrito","ventas","hibernateLazyInitializer", "handler" },allowSetters = true)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente")
	private ClienteVo cliente;
	
	@JsonIgnoreProperties(value={"correoCliente", "hibernateLazyInitializer", "handler" },allowSetters = true)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venta")
	private VentaVo venta;
	
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="id", referencedColumnName = "id")
	private DireccionPedidoVo direccion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public AdministradorVo getAdministrador() {
		return administrador;
	}

	public void setAdministrador(AdministradorVo administrador) {
		this.administrador = administrador;
	}

	public ClienteVo getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVo cliente) {
		this.cliente = cliente;
	}
	
	public VentaVo getVenta() {
		return venta;
	}

	public void setVenta(VentaVo venta) {
		this.venta = venta;
	}

	public String getModoAdquirir() {
		return modoAdquirir;
	}

	public void setModoAdquirir(String modoAdquirir) {
		this.modoAdquirir = modoAdquirir;
	}

	public DireccionPedidoVo getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionPedidoVo direccion) {
		this.direccion = direccion;
	}

	private static final long serialVersionUID = 1L;
}
