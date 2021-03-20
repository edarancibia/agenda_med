  
package com.calendar.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayOutputStream;

@Component
public class PdfGeneratorUtil {


	@Autowired
	private TemplateEngine templateEngine;
	
	public void createPdf(String templateName, Map map) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
		     Iterator itMap = map.entrySet().iterator();
		       while (itMap.hasNext()) {
			  Map.Entry pair = (Map.Entry) itMap.next();
		          ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		
		String processedHtml = templateEngine.process(templateName, ctx);
		  FileOutputStream os = null;
		  String fileName = UUID.randomUUID().toString();
	        try {
	            final File outputFile = File.createTempFile(fileName, ".pdf");
	            os = new FileOutputStream(outputFile);

	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(processedHtml);
	            renderer.layout();
	            renderer.createPDF(os, false);
	            renderer.finishPDF();
	            System.out.println("PDF created successfully");
	        }
	        finally {
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException e) { /*ignore*/ }
	            }
	        }
	}
	
	public ByteArrayOutputStream generatePdf(String html) {

		String pdfFilePath = "";
		PdfWriter pdfWriter = null;

		// create a new document
		Document document = new Document();
		try {

			document = new Document();
			// document header attributes
			document.addAuthor("edarancibia");
			document.addAuthor("edarancibia");
			document.addCreationDate();
			document.addProducer();
			document.addCreator("kinns123.github.io");
			document.addTitle("HTML to PDF using itext");
			document.setPageSize(PageSize.LETTER);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);

			// open document
			document.open();

			XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
			xmlWorkerHelper.getDefaultCssResolver(true);
			xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(
					html));
			// close the document
			document.close();
			System.out.println("PDF generated successfully");

			return baos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}


