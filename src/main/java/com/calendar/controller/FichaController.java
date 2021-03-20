package com.calendar.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;

import com.calendar.entities.Ficha;
import com.calendar.entities.Profesional;
import com.calendar.impl.FichaServiceImpl;
import com.calendar.impl.ProfesionalServiceImpl;
import com.calendar.service.PacienteServiceImpl;
import com.calendar.util.PdfGeneratorUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/ficha")
@SessionAttributes({"activeUser","centro","activeIdUser","activePerfil","activeProf","activeCentro"})
public class FichaController {
	
	private static final Log LOG = LogFactory.getLog(FichaController.class);
	
	@Autowired
	public FichaServiceImpl fichaService;
	
	@Autowired
	PdfGeneratorUtil pdfGeneratorUtil;
	
	@Autowired
	PacienteServiceImpl pacienteService;
	
	@Autowired
	ProfesionalServiceImpl profesionalService;
	
	@GetMapping("/{rut_num}/{idEvento}")
	public ModelAndView index(HttpSession session, Model model, @PathVariable int rut_num,@PathVariable int idEvento) {
		ModelAndView modelAndView = new ModelAndView("ficha");
		model.addAttribute("activeUser",session.getAttribute("username"));
		model.addAttribute("activePerfil",session.getAttribute("tipoUser"));
		model.addAttribute("activeProf",session.getAttribute("activeProf"));
		model.addAttribute("rut_pac",rut_num);
		model.addAttribute("idEvento",idEvento);
		return modelAndView;
	}
	
	@GetMapping("/history/{idFicha}")
	public ModelAndView history(HttpSession session, Model model, @PathVariable Long idFicha) {
		ModelAndView modelAndView = new ModelAndView("ficha");
		model.addAttribute("activeUser",session.getAttribute("username"));
		model.addAttribute("activePerfil",session.getAttribute("tipoUser"));
		model.addAttribute("activeProf",session.getAttribute("idProfesional"));
		
		return modelAndView;
	}
	
	//SAVE
	@PostMapping("/save")
	public ResponseEntity<?> addFicha(@RequestBody Ficha ficha){
		if(ficha != null) {
			ficha = fichaService.addFicha(ficha);
			return ResponseEntity.status(HttpStatus.CREATED).body(ficha);	
		}else {
			return null;
		}
	}
	
	//get by pac
	@GetMapping("get-by-pac/{rutPac}")
	public @ResponseBody List<Map<String, Object>> getByPaciente(@PathVariable int rutPac){
		List<Map<String, Object>> fichas = fichaService.findFichasByRutPac(rutPac);
		return fichas;
	}
	

	//get by id
	@GetMapping("/get-by-id/{idFicha}")
	public ResponseEntity<?> getById(@PathVariable Long idFicha){
		Ficha ficha = fichaService.findFichaByIdFicha(idFicha);
		
		if(ficha == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Ficha>(ficha,HttpStatus.OK);
		}
	}
	
	@GetMapping("/export/{idficha}")
	HttpEntity<byte[]> createPdf(
            @PathVariable("idficha") String fileName, HttpServletResponse response, HttpSession session) throws IOException {


		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();

		/* next, get the Template */
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		Template t = ve.getTemplate("templates/pdf.vm", "UTF-8");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		
		int idficha = Integer.parseInt(fileName);
		Ficha ficha = fichaService.findFichaByIdFicha(Long.valueOf(idficha));
		//Map<String, Object> cab = atMedService.getDatosPacientePdf(idficha);
		Map<String, Object> pac = pacienteService.getPacienteByFicha(idficha);
		
		context.put("nombre", pac.get("nombre").toString());
		context.put("rut", pac.get("dni").toString());
		
		context.put("antece",ficha.getAntecedentes().toString());
		context.put("motivo", ficha.getMotivo().toString());
		context.put("exfi", ficha.getExamenFisico().toString());
		context.put("diag", ficha.getDiagnostico().toString());
		context.put("ind", ficha.getIndicaciones().toString());
		context.put("solex", ficha.getSolExamen().toString());
		
		
		Profesional prof = profesionalService.findProfesionalByFkIdUsuario((Long) session.getAttribute("activeProf"));
		LOG.info("datos: "+ session.getAttribute("activeProf"));
		String usu = prof.getNombre() +' '+ prof.getA_pat() +' '+ prof.getA_mat();	
		context.put("medico", "DR(A). " + usu.toString());
		
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		/* show the World */
		System.out.println(writer.toString());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		baos = pdfGeneratorUtil.generatePdf(writer.toString());

		HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_PDF);
	    header.set(HttpHeaders.CONTENT_DISPOSITION,
	                   "attachment; filename=" + fileName.replace(" ", "_"));
	    header.setContentLength(baos.toByteArray().length);

	    return new HttpEntity<byte[]>(baos.toByteArray(), header);

	}
}
