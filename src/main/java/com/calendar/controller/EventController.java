package com.calendar.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.calendar.entities.Events2;
import com.calendar.entities.Profesional;
import com.calendar.entities.User;
import com.calendar.entities.UsuarioCentro;
import com.calendar.impl.ProfesionalServiceImpl;
import com.calendar.impl.UsuarioCentroServiceImpl;
import com.calendar.repository.ClinicaJpaRepository;
import com.calendar.repository.EventJpaRepository;
import com.calendar.service.EventService;
import com.calendar.service.EventServiceImpl;
import com.calendar.service.UserServiceImpl;

@RestController
@CrossOrigin(origins="*")
@SessionAttributes({"activeUser","centro","activeIdUser","activePerfil","activeProf","activeCentro"})
public class EventController {

	private static final Log LOG = LogFactory.getLog(EventController.class);
	
	@Autowired
	private EventJpaRepository eventRepository;
	
	@Autowired
	@Qualifier("eventServiceImpl")
	private EventServiceImpl eventService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserServiceImpl userService;
	
	@Autowired
	@Qualifier("usuarioCentroServiceImpl")
	private UsuarioCentroServiceImpl usuarioCentro;
	
	@Autowired
	public ProfesionalServiceImpl profesionalService;
	
	public long centro; 
	public int idUsuario;
	public int tipoUsuario;
	
	@GetMapping("/")
	public ModelAndView loginForm(User user) {
		return new ModelAndView("login");
	}
	
	@PostMapping("/home")
	public ModelAndView login(@ModelAttribute(name="user") User user,
			@RequestParam("email") String email,
			@RequestParam("pass") String pass,
			HttpSession session, Model model) {
		
		if(null != userService.findByEmailAndPass(email, pass)) {
			
			User username = userService.findUserByEmail(email);
			session.setAttribute("username",username.getNombre()+' '+ username.getApat()+' '+ username.getAmat());
			
			UsuarioCentro usCentro = usuarioCentro.findByIdUsuario(username.getIdusuario());
			idUsuario = (int) usCentro.getIdUsuario();
			tipoUsuario = username.getPerfil();
			session.setAttribute("tipoUser", tipoUsuario);
			
			centro = usCentro.getIdCentro();
			session.setAttribute("activeCentro", centro);
			
			model.addAttribute("activeUser",session.getAttribute("username"));
			model.addAttribute("activePerfil",session.getAttribute("tipoUser"));
			model.addAttribute("activeCentro",centro);
			
			String vista;
			
			if(tipoUsuario == 1) {
				Profesional prof = profesionalService.findProfesionalByFkIdUsuario(idUsuario);
				LOG.info("prof: " + prof);
				model.addAttribute("activeProf", username.getIdusuario());
				session.setAttribute("idProfesional", prof.getIdProfesional());
				session.setAttribute("activeRutProf", username.getIdusuario());
				vista = "calendarProf";
				return new ModelAndView(vista);
			}else {
				vista = "calendar";
				return new ModelAndView(vista);
			}
			
		}else {
			LOG.info("no pasa");
			return this.loginForm(user);
		}
	}
	
	@RequestMapping(value="/calendar", method=RequestMethod.GET) 
	public ModelAndView jsoncalendar(HttpSession session) {
		int tipoUser = (int) session.getAttribute("tipoUser");
		if(tipoUser == 1){
			return new ModelAndView("calendarProf");
		}else {
			return new ModelAndView("calendar");
		}
		
	}
	
	
	@RequestMapping(value="/allevents", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> events2(){
		List<Map<String, Object>> eventos = eventService.getAllEvents();
		//LOG.info("datos:"+ eventos);
		return eventos;
	}
	
	@GetMapping("/alleventsByProf/{idProf}")
	public @ResponseBody List<Map<String, Object>> events(@PathVariable int idProf){
		List<Map<String, Object>> eventos = eventService.getEventsByProf(idProf);
		LOG.info("datos:"+ eventos);
		return eventos;
	}
	
	@PostMapping("/addevent")
	public ResponseEntity<Events2> addEvent(@RequestBody Events2 events){
		Events2 created = eventRepository.save(events);
		return new ResponseEntity<Events2>(created,HttpStatus.OK);
	}
	
	//CONFIRMA CITA
	@PutMapping("/confirmar/{idEvento}")
	public ResponseEntity<Events2> confirmaEvento(@PathVariable Long idEvento, @RequestBody Events2 events){
		Events2 eventoDb = eventService.findEvents2ById(idEvento);
		
		if(eventoDb == null) {
			return ResponseEntity.notFound().build();
		}
		
		eventoDb.setEstado(events.getEstado());
		eventService.addEvent(eventoDb);
		return ResponseEntity.notFound().build();
	}
	
	//CANCELA CITA
	@PutMapping("/cancelar/{idEvento}")
	public ResponseEntity<Events2> cancelaEvento(@PathVariable Long idEvento, @RequestBody Events2 events){
		Events2 evento = eventService.findEvents2ById(idEvento);
		
		if(evento == null) {
			return ResponseEntity.notFound().build();
		}
		
		evento.setEstado(0);
		eventService.addEvent(evento);
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/getProfetionals")
	public @ResponseBody List<Map<String, Object>> getProfetionals(){
		//carga lista de profesionales
		int idCentro = (int)centro;
		List<Map<String, Object>> profs = userService.getUserProfesionals(idCentro);
		LOG.info("Profesionales: "+ profs);
		return profs;
	}
}
