package com.calendar.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.entities.Paciente;
import com.calendar.entities.Profesional;
import com.calendar.entities.User;
import com.calendar.impl.ProfesionalServiceImpl;
import com.calendar.service.ProfesionalService;
import com.calendar.service.UserServiceImpl;

@RestController
@RequestMapping("/profesional")
public class ProfesionalController {

	@Autowired
	@Qualifier("profesionalServiceImpl")
	private ProfesionalServiceImpl profesionalService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserServiceImpl userService;
	
	private static final Log LOG = LogFactory.getLog(ProfesionalController.class);
	
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
	
}
