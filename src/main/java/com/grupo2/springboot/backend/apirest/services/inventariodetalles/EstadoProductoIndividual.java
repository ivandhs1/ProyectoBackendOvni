package com.grupo2.springboot.backend.apirest.services.inventariodetalles;

public class EstadoProductoIndividual {
	
	private String nombre;
	private boolean estado;
	private int cantidad;
	public EstadoProductoIndividual(String nombre, boolean estado, int cantidad) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return "EstadoProductoIndividual [nombre=" + nombre + ", estado=" + estado + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
}
