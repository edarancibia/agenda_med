package com.calendar.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.calendar.entities.Events2;
import com.calendar.entities.Paciente;
import com.calendar.service.PacienteService;
import com.calendar.service.PacienteServiceImpl;

@RestController
@CrossOrigin(origins = "https://clinic-calendar.herokuapp.com")
public class PacienteController {
	
	private static final Log LOG = LogFactory.getLog(PacienteController.class);

	@Autowired
	public PacienteServiceImpl pacienteService;
	
	@GetMapping("/paciente/{rutnum}")
    public List<Map<String, Object>>  findByRut(@PathVariable("rutnum") int rutnum){
		List<Map<String, Object>>  paciente = pacienteService.obtienePorRut(rutnum);
        return paciente;
    }
	
	
	@RequestMapping(value="addpaciente", method=RequestMethod.POST)
	public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente){
		LOG.info("datos:"+ paciente );
		pacienteService.addPaciente(paciente);
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}
	
}
