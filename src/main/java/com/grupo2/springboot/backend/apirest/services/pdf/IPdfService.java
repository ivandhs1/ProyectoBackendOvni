package com.grupo2.springboot.backend.apirest.services.pdf;
import javax.servlet.http.HttpServletResponse;

import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.lowagie.text.Document;
public interface IPdfService {

	public Document crearFactura(VentaVo venta, HttpServletResponse response );
}
