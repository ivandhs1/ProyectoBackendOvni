package com.grupo2.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.grupo2.springboot.backend.apirest.services.contabilidadanual.IContabilidadAnualService;
import com.grupo2.springboot.backend.apirest.services.contabilidaddiaria.IContabilidadDiariaService;
import com.grupo2.springboot.backend.apirest.services.contabilidadmensual.IContabilidadMensualService;
import com.grupo2.springboot.backend.apirest.services.inventariodetalles.EstadoProducto;
import com.grupo2.springboot.backend.apirest.services.inventariodetalles.EstadoProductoIndividual;
import com.grupo2.springboot.backend.apirest.services.inventariodetalles.IinventarioDetallesService;
import com.grupo2.springboot.backend.apirest.services.pdf.IPdfService;
import com.grupo2.springboot.backend.apirest.services.venta.IVentaService;
import com.grupo2.springboot.backend.apirest.util.service.IEnviosCorreo;

import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = { "http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089" })
@RestController
@RequestMapping("/apiVenta")
public class VentaRestController {

	@Autowired
	private IVentaService ventaService;

	@Autowired
	private IinventarioDetallesService inventarioService;

	@Autowired
	private IContabilidadDiariaService contabilidadDiariaService;

	@Autowired
	private IContabilidadMensualService contabilidadMensualService;

	@Autowired
	private IContabilidadAnualService contabilidadAnualService;

	@Autowired
	private IEnviosCorreo envioCorreo; 

	@Autowired
	private IPdfService pdfService;

	// http://localhost:8080/apiVenta/ventas
	@GetMapping("/ventas")
	public ResponseEntity<?> ventas() {
		List<VentaVo> ventas = null;
		Map<String, Object> response = new HashMap<>();
		try {
			ventas = ventaService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<VentaVo>>(ventas, HttpStatus.OK);
	}

	// http://localhost:8080/apiVenta/venta/id
	@GetMapping("/venta/{id}")
	public ResponseEntity<?> getVenta(@PathVariable Integer id) {
		VentaVo venta = null;

		Map<String, Object> response = new HashMap<>();

		try {
			venta = ventaService.findById(id);
			if (venta == null) {
				response.put("mensaje", "No se encontro la venta");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<VentaVo>(venta, HttpStatus.OK);
	}

	// http://localhost:8080/apiVenta/venta/tipo
	@PostMapping("/venta/{tipo}")
	public ResponseEntity<?> create(@RequestBody VentaVo venta, @PathVariable String tipo) {
		VentaVo ventaNew = null;
		VentaVo ventaReto = null;

		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
		try {
			
			venta.setFechaVenta(LocalDateTime.parse(dtf.format(LocalDateTime.now()),dtf));
			
			
			EstadoProducto estadoProducto=inventarioService.disminuirCantidad(venta);
			if(estadoProducto.isEstado()==true) {
				venta.setCantidadVenta();
				venta.setPrecioVenta();
				ventaNew = ventaService.save(venta);
				if(ventaNew.getCorreoCliente().getCorreoCliente().equals("correoClienteOvni@gmail.com")) {
					ventaService.gestorAsignarContabilidad(ventaNew, venta);
				}
				ventaReto = ventaService.save(ventaNew);

			} else {
				response.put("mensaje", "cantidad insuficiente");
				int contador = 0;
				for (EstadoProductoIndividual estadoProductoI : estadoProducto.getProductos()) {
					if (estadoProductoI.isEstado() == false) {
						contador += 1;
						response.put("producto " + contador,
								estadoProductoI.getNombre()
										+ " no tiene la cantidad que necesita este producto solo cuenta con "
										+ estadoProductoI.getCantidad());
					}
				}
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			ventaReto.setFechaVenta(LocalDateTime.parse(dtf.format(LocalDateTime.now()), dtf));
			envioCorreo.enviarCorreo(ventaReto.getCorreoCliente(), ventaReto);
			envioCorreo.enviarCorreoAdmin(ventaReto,tipo);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "la venta se ha registro con exito");

		response.put("venta", ventaReto);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// http://localhost:8080/apiVenta/factura/id
	@GetMapping("/factura/{id}")
	public void generatePDF(@PathVariable Integer id, HttpServletResponse response) {
		VentaVo venta = ventaService.findById(id);

		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		pdfService.crearFactura(venta, response);
	}

}
