package com.grupo2.springboot.backend.apirest.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;

import javax.activation.DataSource;

import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

public class EnviarCorreo extends Thread implements Serializable {
	private CorreoDTO dto;
	private String username = "cridamador@misena.edu.co";
	private String password = "Cristian20045@";

	public EnviarCorreo(CorreoDTO argo) {
		super("procesoEnvioEmail");
		dto = argo;
	}

	private Session connectServer() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		
		
		props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
		return Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	private String getDirecciones(CorreoDTO dto) {
		StringBuilder direcciones = new StringBuilder();
		boolean primero = true;
		for (String correo : dto.getDestinatarios()) {
			if (primero) {
				direcciones.append(correo);
				primero = !primero;
			} else {
				direcciones.append(",");
				direcciones.append(correo);
			}
		}
		return direcciones.toString();
	}

	/*
	 * private String getDirecciones(CorreoDTO dto) {
	 * return dto.getDireccionCorreo().stream().collect(Collectors.joining(","));
	 * }
	 */
	private void sendEmail(CorreoDTO dto) {
		// Obtenemos las direcciones/destinatarios
		String direcciones = getDirecciones(dto);
		// Obtenemos la conexion al servidor de correos
		Session session = connectServer();
		try {
			MimeMessage msg = new MimeMessage(session);
			// Agregamos los headers necesarios
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			// Asignamos el asunto del correo
			msg.setSubject(dto.getTitulo(), "UTF-8");
			// Asignamos la hora de creacion del correo
			msg.setSentDate(new Date());

			Multipart multiparte = new MimeMultipart();

			// Creamos el cuerpo del mensaje
			BodyPart cuerpoMensaje = new MimeBodyPart();
			cuerpoMensaje.setContent(dto.getContenido(), "text/html");
			multiparte.addBodyPart(cuerpoMensaje);

			// Validamos si tenemos adjuntos
			if (dto.getAdjuntos() == null ? false : !dto.getAdjuntos().isEmpty()) {
				// Si tenemos adjuntos los agregamos al mensaje
				for (File file : dto.getAdjuntos()) {
					cuerpoMensaje = new MimeBodyPart();
					cuerpoMensaje.setDataHandler(new DataHandler(new FileDataSource(file)));
					cuerpoMensaje.setFileName(file.getName());
					multiparte.addBodyPart(cuerpoMensaje);
				}
			}
			msg.setContent(multiparte);
			msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(direcciones));

			// Agregamos el origen del mensaje (nuestro email)
			msg.setFrom(new InternetAddress(username));

			// Enviamos el mensaje
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		sendEmail(dto);
	}

}
