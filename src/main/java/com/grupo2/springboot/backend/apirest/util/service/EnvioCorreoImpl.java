package com.grupo2.springboot.backend.apirest.util.service;

import java.io.File;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.VentaClienteVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.grupo2.springboot.backend.apirest.util.CorreoDTO;
import com.grupo2.springboot.backend.apirest.util.EnviarCorreo;

@Service
public class EnvioCorreoImpl implements IEnviosCorreo {

	@Override
	public void enviarCorreo(ClienteVo cliente, VentaVo venta) {
		CorreoDTO dto = new CorreoDTO();

		String subject = "Compra ovnivinos";
		String body = "Hola " + cliente.getNombreCliente() + " " + cliente.getApellidoCliente()
				+ " su compra se realizo con exito \n"
				+ " para descargar su factura ingrese a http://localhost:8080/apiVenta/factura/"
				+ venta.getCodigoVenta();
		// List<File> adjuntos = Collections.singletonList(new File("PATH_TO_FILE"));
		List<File> adjuntos = null;
		try {

			dto.getDestinatarios().add(cliente.getCorreoCliente());
			dto.setContenido(body);
			dto.setTitulo(subject);
			if (adjuntos != null && !adjuntos.isEmpty())
				dto.setAdjuntos(adjuntos);

			EnviarCorreo enviarCorreo = new EnviarCorreo(dto);
			enviarCorreo.start();
		} catch (Exception e) {
			System.out.println("hola");
		}

	}

	@Override
	public void enviarVerificacion(RecuperarClass data) {
		CorreoDTO dto = new CorreoDTO();

		String subject = "Recuperacion contraseña ovnivinos";
		String body = "Apreciado cliente  para recuperar su contraseña debe: \n" + 
				" - Darle click al siguiente enlace " + data.getUrl()+"/recuperacion/" + data.getCorreoEncrypt();

		List<File> adjuntos = null;
		try {

			dto.getDestinatarios().add(data.getCorreo());
			dto.setContenido(body);
			dto.setTitulo(subject);
			if (adjuntos != null && !adjuntos.isEmpty())
				dto.setAdjuntos(adjuntos);

			EnviarCorreo enviarCorreo = new EnviarCorreo(dto);
			enviarCorreo.start();
		} catch (Exception e) {
		}
		
	}

	@Override
	public void enviarCorreoAdmin(VentaVo venta, String tipo) {
		CorreoDTO dto = new CorreoDTO();

		String subject = "Venta ovnivinos";
		String body = "Se ha realizado una venta en el aplicativo de " + tipo + " del cliente " + venta.getCorreoCliente().getCorreoCliente()
				+ " para " + venta.getCorreoCliente().getDireccionCliente() + " pidio \n";
		
		for (VentaClienteVo itemVenta : venta.getVentas()) {
			body = body +itemVenta.getCantidadProducto()+ itemVenta.getCodigoProducto().getNombreProducto() + "\n";
		}
		// List<File> adjuntos = Collections.singletonList(new File("PATH_TO_FILE"));
		List<File> adjuntos = null;
		try {

			dto.getDestinatarios().add("crissis2004@gmail.com");
			dto.setContenido(body);
			dto.setTitulo(subject);
			if (adjuntos != null && !adjuntos.isEmpty())
				dto.setAdjuntos(adjuntos);

			EnviarCorreo enviarCorreo = new EnviarCorreo(dto);
			enviarCorreo.start();
		} catch (Exception e) {

		}
		
	}

	@Override
	public void enviarAyuda(String correo, String problema, String descripcion) {
		CorreoDTO dto = new CorreoDTO();

		String subject = "Notificacion problema cliente";
		String body = "";
		if(descripcion.equals("no")) {
			body = "El cliente "+ correo +" ha presentado la anomalia de "+ problema +" : \n";
		}else {
			body = "El cliente "+ correo +" ha presentado una anomalia con la descripcion: \n"+ descripcion+" ";
		}

		List<File> adjuntos = null;
		try {

			dto.getDestinatarios().add("crissis2004@gmail.com");
			dto.setContenido(body);
			dto.setTitulo(subject);
			if (adjuntos != null && !adjuntos.isEmpty())
				dto.setAdjuntos(adjuntos);

			EnviarCorreo enviarCorreo = new EnviarCorreo(dto);
			enviarCorreo.start();
		} catch (Exception e) {
		}
		
	}
}
