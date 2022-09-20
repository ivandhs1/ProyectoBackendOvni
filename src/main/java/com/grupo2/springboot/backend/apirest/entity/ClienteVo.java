package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cliente")
public class ClienteVo implements Serializable{
	
	@Id
	@Column(name="correo_cliente")
	private String correoCliente;
	
	@Column(name="nombre_cliente")
	private String nombreCliente;
	
	@Column(name="apellido_cliente")
	private String apellidoCliente;
	
	@Column(name="direccion_cliente")
	private String direccionCliente;
	
	@Column(name="telefono_cliente")
	private String telefonoCliente;
	
	@Column(name="password_cliente")
	private String passwordCliente;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private Usuario user;
	
	@JsonIgnoreProperties(value={"cliente","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToOne(fetch=FetchType.LAZY, mappedBy="cliente", cascade=CascadeType.ALL)
	private CarritoClienteVo carrito;
	
	@JsonIgnoreProperties(value={"correoCliente","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy="correoCliente", cascade=CascadeType.ALL)
	private List<VentaVo> ventas;

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getPasswordCliente() {
		return passwordCliente;
	}

	public void setPasswordCliente(String passwordCliente) {
		this.passwordCliente = passwordCliente;
	}

	public CarritoClienteVo getCarrito() {
		return carrito;
	}

	public void setCarrito(CarritoClienteVo carrito) {
		this.carrito = carrito;
	}

	public List<VentaVo> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentaVo> ventas) {
		this.ventas = ventas;
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	private static final long serialVersionUID = 1L;
	
}
