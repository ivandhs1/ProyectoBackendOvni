package com.grupo2.springboot.backend.apirest.services.inventariodetalles;

import java.util.List;

public class EstadoProducto {
	
	private boolean estado;
	private List<EstadoProductoIndividual> productos;
	
	public EstadoProducto(boolean estado, List<EstadoProductoIndividual> productos) {
		super();
		this.estado = estado;
		this.productos = productos;
	}
	
	public boolean isEstado() {
		return estado;
	}
	
	public void setEstado() {
		boolean elEstado = true;
		for(EstadoProductoIndividual estadoProducto: this.productos) {
			if(estadoProducto.isEstado()==false) {
				elEstado = false;
				break;
			}
		}
		this.estado = elEstado;
	}
	
	public List<EstadoProductoIndividual> getProductos() {
		return productos;
	}
	
	public void setProductos(List<EstadoProductoIndividual> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "EstadoProducto [estado=" + estado + ", productos=" + productos + "]";
	}
	
	
	
}
