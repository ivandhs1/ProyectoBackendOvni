package com.grupo2.springboot.backend.apirest.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "administrador")
public class AdministradorVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	// Al no ser autogenerado no se le coloca la anotacion
	// @GeneratedValue(strategy=GenerationType=?)
	@Column(name = "correo_admin")
	private String correoAdmin;

	@Column(name = "nombre_admin")
	private String nombreAdmin;

	@Column(name = "apellido_admin")
	private String apellidoAdmin;

	@Column(name = "direccion_admin")
	private String direccionAdmin;

	@Column(name = "telefono_admin")
	private String telefonoAdmin;

	@Column(name = "password_admin")
	private String passwordAdmin;
	
	@Column(name = "estado")
	private String estado;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private Usuario user;
	

	@JsonIgnoreProperties(value={"administradorCompra","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy="administradorCompra", cascade=CascadeType.ALL)
	private List<CompraVo> comprasAdmin;

	public String getCorreoAdmin() {
		return correoAdmin;
	}

	public void setCorreoAdmin(String correoAdmin) {
		this.correoAdmin = correoAdmin;
	}

	public String getNombreAdmin() {
		return nombreAdmin;
	}

	public void setNombreAdmin(String nombreAdmin) {
		this.nombreAdmin = nombreAdmin;
	}

	public String getApellidoAdmin() {
		return apellidoAdmin;
	}

	public void setApellidoAdmin(String apellidoAdmin) {
		this.apellidoAdmin = apellidoAdmin;
	}

	public String getDireccionAdmin() {
		return direccionAdmin;
	}

	public void setDireccionAdmin(String direccionAdmin) {
		this.direccionAdmin = direccionAdmin;
	}

	public String getTelefonoAdmin() {
		return telefonoAdmin;
	}

	public void setTelefonoAdmin(String telefonoAdmin) {
		this.telefonoAdmin = telefonoAdmin;
	}

	public String getPasswordAdmin() {
		return passwordAdmin;
	}

	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<CompraVo> getComprasAdmin() {
		return comprasAdmin;
	}

	public void setComprasAdmin(List<CompraVo> comprasAdmin) {
		this.comprasAdmin = comprasAdmin;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
}
