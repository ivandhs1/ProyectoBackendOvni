        package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "compra")
public class CompraVo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_compra")
	private int codigoCompra;

	@Column(name = "precio_compra")
	private double precioCompra;

	@Column(name = "cantidad_compra")
	private int cantidadCompra;

	@Column(name = "fecha_compra")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd-HH:mm:ss")
	private LocalDateTime fechaCompra;

	@JsonIgnoreProperties(value = {"comprasAdmin", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "administrador_compra")
	private AdministradorVo administradorCompra;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_registro_contabilidad_diaria_compra")
	private ContabilidadDiariaVo idRegistroContabilidadDiaria;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "compra")
	private List<CompraAdminVo> compras;

	public int getCodigoCompra() {
		return codigoCompra;
	}

	public void setCodigoCompra(int codigoCompra) {
		this.codigoCompra = codigoCompra;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra() {
		double precio = 0;
		
		for(CompraAdminVo detallesCompra : this.getCompras()) {
			detallesCompra.setPrecioCompraDetalle();
			precio += detallesCompra.getPrecioCompraDetalle();
		}
		this.precioCompra = precio;
	}

	public int getCantidadCompra() {
		return cantidadCompra;
	}

	public void setCantidadCompra() {
		int cantidad = 0;
		for(CompraAdminVo detallesCompra : this.getCompras()) {
			cantidad += detallesCompra.getCantidadProducto();
		}
		
		this.cantidadCompra = cantidad;
	}

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public AdministradorVo getAdministradorCompra() {
		return administradorCompra;
	}

	public void setAdministradorCompra(AdministradorVo administradorCompra) {
		this.administradorCompra = administradorCompra;
	}

	public ContabilidadDiariaVo getIdRegistroContabilidadDiaria() {
		return idRegistroContabilidadDiaria;
	}

	public void setIdRegistroContabilidadDiaria(ContabilidadDiariaVo idRegistroContabilidadDiaria) {
		this.idRegistroContabilidadDiaria = idRegistroContabilidadDiaria;
	}

	public List<CompraAdminVo> getCompras() {
		return compras;
	}

	public void setCompras(List<CompraAdminVo> compras) {
		this.compras = compras;
	}

	private static final long serialVersionUID = 1L;	

}
