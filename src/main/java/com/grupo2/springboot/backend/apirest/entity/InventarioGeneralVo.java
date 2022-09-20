package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="inventario_general")
public class InventarioGeneralVo implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_registro")
	private int idRegistro; 
	
	@Column(name="cantidad_producto")
	private int cantidadProducto;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_producto")
	private ProductoVo codigoProducto;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_registro")
	private List<InventarioDetallesVo> detalles;

	public int getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto() {
		int cantidadTotal = 0;
		if(this.getDetalles().size()>0) {
			for (InventarioDetallesVo inventarioDetallesVo : this.getDetalles()) {
				cantidadTotal += inventarioDetallesVo.getCantidadProducto();
			}
		}
		this.cantidadProducto = cantidadTotal;
	}

	public ProductoVo getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(ProductoVo codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public List<InventarioDetallesVo> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<InventarioDetallesVo> detalles) {
		this.detalles = detalles;
	}
	
	private static final long serialVersionUID = 1L;
	
}
