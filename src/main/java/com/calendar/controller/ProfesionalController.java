package com.calendar.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.calendar.entities.Paciente;
import com.calendar.entities.Profesional;
import com.calendar.entities.User;
import com.calendar.impl.ProfesionalServiceImpl;
import com.calendar.service.ProfesionalService;
import com.calendar.service.UserServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/profesional")
public class ProfesionalController {

	@Autowired
	@Qualifier("profesionalServiceImpl")
	private ProfesionalServiceImpl profesionalService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserServiceImpl userService;
	
	private static final Log LOG = LogFactory.getLog(ProfesionalController.class);
	
	//vista new profesional
	@GetMapping("/new")
	public ModelAndView newProfesional(HttpSession session, Model model){
		ModelAndView modelAndView = new ModelAndView("profesional");
		LOG.info("usuario: "+ session.getAttribute("username"));
		model.addAttribute("activeUser",session.getAttribute("username"));
		return modelAndView;
	}
	
	//@GetMapping(value = "/verificar/{email}")
	@RequestMapping(value = "/verificar/{email}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<User> findByEmail(@PathVariable("email") String email){
		 
		User user = userService.findUserByEmail(email);
		
		LOG.info("email:" + user);
		if(user == null) {
			 return new ResponseEntity(HttpStatus.NOT_FOUND);
		 }else {
			 return new ResponseEntity<User>(user,HttpStatus.OK); 
		 }
		 
	}
	
	@PostMapping(value = "/new")
	public ResponseEntity<Profesional> addProfesional(@RequestBody Profesional profesional){
		
		profesional = profesionalService.addProfesional(profesional);
		return new ResponseEntity<Profesional>(profesional,HttpStatus.OK);
	}
	
	//lista de profesionales por centro para combo
	//@CrossOrigin(origins = "https://clinic-calendar.herokuapp.com")
	@GetMapping("/get-by-centro/{idCentro}")
	public @ResponseBody List<Map<String, Object>> getByCentro(@PathVariable Long idCentro){
		List<Map<String, Object>> profesionales = profesionalService.getProfByCentro(idCentro);
		return profesionales;
	}
	
	//obtiene nombre profesional para mostrar en calendario
	@GetMapping("/get-by-id/{idProfesional}")
	public ResponseEntity<Profesional> getById(@PathVariable Long idProfesional){
		Profesional prof = profesionalService.findProfesionalByIdProfesional(idProfesional);
		LOG.info("dato: "+ prof);
		return new ResponseEntity<Profesional>(prof,HttpStatus.OK); 
	}
	
}
