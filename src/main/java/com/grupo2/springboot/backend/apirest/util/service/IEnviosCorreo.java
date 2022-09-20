package com.grupo2.springboot.backend.apirest.util.service;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;

public interface IEnviosCorreo {

	public void enviarCorreo(ClienteVo cliente, VentaVo venta);
	
	public void enviarCorreoAdmin(VentaVo venta, String tipo);
	
	public void enviarVerificacion(RecuperarClass data);
	
	public void enviarAyuda(String correo, String problema, String descripcion);

}