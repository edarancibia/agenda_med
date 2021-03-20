package com.calendar.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.calendar.entities.Ficha;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/ficha/history/41")
public class FichaPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Ficha ficha = (Ficha) model.get("rut_pac");
		
		PdfPTable tablaPaciente = new PdfPTable(2);
		tablaPaciente.addCell(ficha.getAntecedentes());
		
		document.add(tablaPaciente);
	}

}
