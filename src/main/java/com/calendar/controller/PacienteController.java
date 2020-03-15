package com.calendar.controller;

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

@Controller
public class PacienteController {
	
	private static final Log LOG = LogFactory.getLog(PacienteController.class);

	@Autowired
	@Qualifier("pacienteServiceImpl")
	private PacienteService pacienteService;
	
	@RequestMapping(value = "/paciente/{rutnum}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Paciente> findByRut(@PathVariable("rutnum") int rutnum){
		Paciente paciente = pacienteService.findByDni(rutnum);
        if (paciente == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
    }
	
	/*@RequestMapping(value="addpaciente", method=RequestMethod.POST)
	public void addPaciente(@RequestBody Paciente paciente){
		LOG.info("datos:"+ paciente );
		pacienteService.addPaciente(paciente);
	}*/
	
	@RequestMapping(value="addpaciente", method=RequestMethod.POST)
	public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente){
		LOG.info("datos:"+ paciente );
		pacienteService.addPaciente(paciente);
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}
	
}
