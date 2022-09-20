package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "venta")
public class VentaVo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_venta")
	private int codigoVenta;

	@Column(name = "precio_venta")
	private double precioVenta;

	@Column(name = "cantidad_venta")
	private int cantidadVenta;

	@Column(name = "fecha_venta")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd-HH:mm:ss")
	private LocalDateTime fechaVenta;

	@JsonIgnoreProperties(value = { "ventas", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "correo_cliente")
	private ClienteVo correoCliente;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_registro_contabilidad_diaria")
	private ContabilidadDiariaVo idRegistroContabilidadDiaria;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "venta")
	private List<VentaClienteVo> ventas;

	public int getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(int codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta() {
		double total = 0;
		for(VentaClienteVo ventas: this.ventas) {
			ventas.setPrecioVentaDetalle();
			total += ventas.getPrecioVentaDetalle();
		}
		this.precioVenta = total;
	}

	public int getCantidadVenta() {
		return cantidadVenta;
	}

	public void setCantidadVenta() {
		int cantidad = 0;
		for(VentaClienteVo ventas : this.ventas) {
			cantidad += ventas.getCantidadProducto();
		}
		this.cantidadVenta = cantidad;
	}

	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public ClienteVo getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(ClienteVo correoCliente) {
		this.correoCliente = correoCliente;
	}

	public ContabilidadDiariaVo getIdRegistroContabilidadDiaria() {
		return idRegistroContabilidadDiaria;
	}

	public void setIdRegistroContabilidadDiaria(ContabilidadDiariaVo idRegistroContabilidadDiaria) {
		this.idRegistroContabilidadDiaria = idRegistroContabilidadDiaria;
	}

	public List<VentaClienteVo> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentaClienteVo> ventas) {
		this.ventas = ventas;
	}
	
	private static final long serialVersionUID = 1L;

}
