package com.grupo2.springboot.backend.apirest.services.pdf;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.entity.VentaClienteVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
@Service
public class PdfServiceImpl implements IPdfService{

	@Override
	public Document crearFactura(VentaVo venta, HttpServletResponse response ) {
		
		Document document = new Document(PageSize.A4.rotate());
        
		try {
			PdfWriter.getInstance(document, response.getOutputStream());
 
            document.open();
            Paragraph p1 = new Paragraph(new Chunk("OvniVinos. \n Barrio Centenario manzana C casa 13 \n Tel. 310-211-1965 \n Armenia-Quindio \n OvniVinos@gmail.com",FontFactory.getFont(FontFactory.HELVETICA, 8)));
            p1.add(new Phrase("                              Ovni_Vinos",FontFactory.getFont(FontFactory.TIMES_BOLD, 32)));
            

            p1.setSpacingAfter((float) 80.0);
            
            document.add(p1);
            String[] bogusData = { "","SL", "FR86000P", "PCGOLD"};
            int NumColumns = 4;

            PdfPTable datatable = new PdfPTable(NumColumns);
            int[] headerwidths = {15, 45, 15, 25}; // percentage
            datatable.setWidths(headerwidths);
            datatable.setWidthPercentage(100); // percentage
            datatable.getDefaultCell().setPadding(3);
            datatable.getDefaultCell().setBorderWidth(2);
            datatable.getDefaultCell().setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            datatable.addCell("ITEM #");
            datatable.addCell("PRODUCTO");
            datatable.addCell("CANTIDAD");
            datatable.addCell("TOTAL");

            datatable.setHeaderRows(1); // this is the end of the table header

            datatable.getDefaultCell().setBorderWidth(1);
            int contador = 1;
            for (VentaClienteVo i : venta.getVentas()) {
            	System.out.println("asd");
            	if (contador % 2 == 1) {
                    datatable.getDefaultCell().setGrayFill(0.9f);
                }
            	for(int x = 0; x < NumColumns; x++) {
            		if(x == 0) {
            			datatable.addCell(contador + "");
            		}
            		else if(x == 1) {

            			datatable.addCell(i.getCodigoProducto().getNombreProducto());
            		}
            		else if(x == 2) {
            			datatable.addCell(i.getCantidadProducto() + "");
            		}
            		else if(x == 3) {
            			datatable.addCell(i.getPrecioVentaDetalle() + "");
            		}
            		
            	}
            	if (contador % 2 == 1) {
                    datatable.getDefaultCell().setGrayFill(1);
                }
			}
            document.add(datatable);
            Paragraph p2 = new Paragraph("                                                                              Total: " + venta.getPrecioVenta(),FontFactory.getFont(FontFactory.TIMES_BOLD, 24));
            p2.setSpacingAfter((float) 40.0);
            document.add(p2);
            
            Paragraph p3 = new Paragraph("                                        Cliente: "+ venta.getCorreoCliente().getNombreCliente() +"                                                   Fecha:   "+venta.getFechaVenta().toString().split("T")[0],FontFactory.getFont(FontFactory.HELVETICA, 14));
            p3.setSpacingBefore((float) 40.0);
            document.add(p3);
            Paragraph p4 = new Paragraph("                                        Direccion: "+ venta.getCorreoCliente().getDireccionCliente() +"                                              Hora: "+venta.getFechaVenta().toString().split("T")[1],FontFactory.getFont(FontFactory.HELVETICA, 14));
            document.add(p4);
            
            Paragraph p5 = new Paragraph("                                        Telefono: " + venta.getCorreoCliente().getTelefonoCliente(),FontFactory.getFont(FontFactory.HELVETICA, 14));
            document.add(p5);
            
            Paragraph p6 = new Paragraph("                                        Correo: " + venta.getCorreoCliente().getCorreoCliente(),FontFactory.getFont(FontFactory.HELVETICA, 14));
            document.add(p6);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException e) {
			e.printStackTrace();
		}

        // step 5: we close the document
        document.close();
        
       
		return document;
	}

}
