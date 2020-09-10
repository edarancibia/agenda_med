package com.calendar.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.calendar.constant.ViewConstant;
import com.calendar.entities.Clinica;
import com.calendar.entities.Invitation;
import com.calendar.entities.MailForm;
import com.calendar.entities.User;
import com.calendar.entities.UsuarioCentro;
import com.calendar.impl.ProfesionalServiceImpl;
import com.calendar.impl.UsuarioCentroServiceImpl;
import com.calendar.repository.ClinicaJpaRepository;
import com.calendar.repository.UserJpaRepository;
import com.calendar.service.ClinicaServiceImpl;
import com.calendar.service.InvitationServiceImpl;
import com.calendar.service.SendMailService;
import com.calendar.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Log LOG = LogFactory.getLog(UserController.class);
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserServiceImpl userService;
	
	@Autowired
	@Qualifier("invitationServiceImpl")
	private InvitationServiceImpl invitationServiceImpl;
	
	@Autowired
	UserJpaRepository userRepository;
	
	@Autowired
	private SendMailService mailService;
	
	@Autowired
	private ClinicaJpaRepository clinicaRepo;
	
	@Autowired
	@Qualifier("profesionalServiceImpl")
	private ProfesionalServiceImpl profesionalServiceImpl;
	
	@Autowired
	@Qualifier("usuarioCentroServiceImpl")
	private UsuarioCentroServiceImpl usuarioCentroServiceImpl;
	
	@Autowired
	@Qualifier("clinicaServiveImpl")
	private ClinicaServiceImpl clinicaServiceImpl;
	
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	
	
	@GetMapping("/register")
	public String register(Model model,User user,@RequestParam(name="error",required = false)String error,
			@RequestParam(name="error",required = false)String errormail,Clinica clinica){
		LOG.info("METHOD: register() --PARAMS: errormail="+ errormail);
		model.addAttribute("errormail",error);
		model.addAttribute("error",errormail);
		return ViewConstant.REGISTER;
	}
	
	@GetMapping("/invitation")
	public String invitacion(Invitation invitation, HttpSession session, Model model) {
		model.addAttribute("activeUser",session.getAttribute("username"));
		model.addAttribute("activePerfil",session.getAttribute("tipoUser"));
		return "invitation";
	}
	
	@PostMapping("/adduser")
	public String addUser(@ModelAttribute(name = "user") User user, Clinica clinica,
			Model model,@ModelAttribute(name="form") MailForm form,
			@RequestParam("email") String email,
			@RequestParam("perfil") int perfil,
			@RequestParam("nombreClinica") String nombreClinica,@RequestParam("direccionClinica") String direccionClinica){
		
		if(null != userService.findUserByEmail(email)){
			return "redirect:/user/register?errormail";
		}else{
		
			//user.setPass(passwordEncoder.encode(user.getPass()));
			if(null != userService.addUser(user)){
				if(user.getPerfil() == 0) {
					LOG.info("no es profesional");
					
				}else {
					LOG.info("es profesional");
					LOG.info("clinica:" + clinica);
					
				}
				//DEBE PREGUNTAR QUE PERFIL TIENE,SI ES PROFESIONAL DEBE GUARDAR EN LA TABLA DEL MISMO NOMBRE
				model.addAttribute("result", 1);
				
				//String message = form.getBody() +"\n\n Datos de contacto: " + "\nNombre: " + form.getName() + "\nE-mail: " + form.getMail();
		        String message = "Bienvenido a Agenda Online, para inciar sesión haz click aqui: \n" + "http://localhost:8080/calendar";
				String subject = "Bienvenido";
		        //mailService.sendMail("erwin2211@hotmail.com",email,subject,message);
				
				clinicaServiceImpl.addClinica(clinica);
				
				//long idCentro = clinicaNew.getId();
				//long idUsuario = user.getIdusuario();
				LOG.info("id usuario:" + user.getIdusuario() +' '+ "idCentro:" + clinica.getId());
				usuarioCentroServiceImpl.addUsuarioCentro(user.getIdusuario(), clinica.getId());
				
		        return "register";
			}else {
				model.addAttribute("result", 0);
			}
		}
		return "register";
	}
	
	@PostMapping("/sedninvitation")
	public String sendInvitation(@ModelAttribute(name = "invitation") Invitation invitation, Model model,
			@RequestParam("email") String email_inv,
			@RequestParam("perfil") String perfil_inv) {
		
		if(null != email_inv) {
			if(null != invitationServiceImpl.addInvitation(invitation)) {
				
				//envia correo al invitado
				String message = "Has sido invitado a utilizar Agenda Online, para inciar sesión haz click aqui: \n" + "http://localhost:8080/user/invitationregister";
				String subject = "Invitación a utilizar Agenda Online";
		        mailService.sendMail("erwin2211@hotmail.com",email_inv,subject,message);
			}
		}else {
			return "invitation";
		}

		return "invitation";
	}
	
	@GetMapping("/profesional")
	public String profesional() {
		return "profesional";
	}
	
}
