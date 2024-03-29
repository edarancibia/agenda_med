package com.calendar.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

import com.calendar.constant.ViewConstant;
import com.calendar.entities.Clinica;
import com.calendar.entities.Invitation;
import com.calendar.entities.MailForm;
import com.calendar.entities.Profesional;
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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/user")
@SessionAttributes({"activeUser","centro","activeIdUser","activePerfil","activeProf","activeCentro"})
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
	@Qualifier("profesionalServiceImpl")
	private ProfesionalServiceImpl profesionalServiceImpl;
	
	@Autowired
	@Qualifier("usuarioCentroServiceImpl")
	private UsuarioCentroServiceImpl usuarioCentroServiceImpl;
	
	@Autowired
	@Qualifier("clinicaServiveImpl")
	private ClinicaServiceImpl clinicaServiceImpl;
	
	@Autowired
	private InvitationServiceImpl invitationService;
	
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	
	
	@GetMapping("/register")
	public String register(Model model,User user,@RequestParam(name="errormail",required = false)String errormail,
			@RequestParam(name="success",required = false)String success,Clinica clinica){
		model.addAttribute("success",success);
		model.addAttribute("errormail",errormail);
		return ViewConstant.REGISTER;
	}
	
	@GetMapping("/invitation")
	public ModelAndView invitacion(Invitation invitation, HttpSession session, Model model,
			@RequestParam(name="errormail",required = false)String errormail,
			@RequestParam(name="success",required = false)String success) {
		ModelAndView mv = new ModelAndView("invitation");
		model.addAttribute("success",success);
		model.addAttribute("errormail",errormail);
		model.addAttribute("activeUser",session.getAttribute("username"));
		model.addAttribute("activePerfil",session.getAttribute("tipoUser"));
		return mv;
	}
	
	//registro de usuario desde register.html
	@PostMapping("/adduser")
	public String addUser(@ModelAttribute(name = "user") User user, Clinica clinica,
			Model model,@ModelAttribute(name="form") MailForm form,
			@RequestParam("email") String email,
			@RequestParam("perfil") int perfil,
			@RequestParam("nombreClinica") String nombreClinica,@RequestParam("direccionClinica") String direccionClinica){
		
		User userDb = userService.findUserByEmail(email);
		if(userDb != null){
			model.addAttribute("errormail","el correo ya existe");
			LOG.info("usuario ya existe "+ model.getAttribute("errormail"));
			return "redirect:/user/register?errormail=1";
		}else{
			user.setVigente(1);
			user.setCreated_at(new Date());
			//user.setPass(passwordEncoder.encode(user.getPass()));
			if(null != userService.addUser(user)){
				//DEBE PREGUNTAR QUE PERFIL TIENE,SI ES PROFESIONAL DEBE GUARDAR EN LA TABLA DEL MISMO NOMBRE
				if(user.getPerfil() == 0) {
					LOG.info("no es profesional");
					clinicaServiceImpl.addClinica(clinica);
				}else {
					
					clinicaServiceImpl.addClinica(clinica);
					
					Profesional profesional = new Profesional();
					profesional.setFkIdUsuario(user.getIdusuario());
					profesional.setNombre(user.getNombre().toUpperCase());
					profesional.setA_pat(user.getApat().toUpperCase());
					profesional.setA_mat(user.getAmat().toUpperCase());
					profesional.setEmail(user.getEmail());
					profesional.setCod_centro(clinica.getId());
					profesional.setVigente(1);
					profesionalServiceImpl.addProfesional(profesional);
					
					usuarioCentroServiceImpl.addUsuarioCentro(user.getIdusuario(), clinica.getId());
					LOG.info("profesional guardado ok");
					
				}
				
				model.addAttribute("result", 1);
				model.addAttribute("success","El usuario fue registrado exitosamente!");
				
		        String message = "Bienvenido a Clinic Calendar, para inciar sesión haz click aqui: \n" + "https://clinic-calendar.herokuapp.com";
				String subject = "Bienvenido a Clinic Calendar";
		        mailService.sendMail("clinic-calendar@outlook.com",email,subject,message);
				
				return "redirect:/user/register?success=1";
			}else {
				model.addAttribute("result", 0);
			}
		}
		return "register";
	}
	
	@PostMapping("/sendninvitation")
	public String sendInvitation(@ModelAttribute(name = "invitation") Invitation invitation, Model model,
			@RequestParam("email") String email_inv,
			@RequestParam("perfil") String perfil_inv, HttpSession session) {
		
		if(null != email_inv) {
			if( null != userService.findUserByEmailAndVigente(email_inv, 1)) {
				//el usuario ya está registrado
				return "redirect:/user/invitation?errormail=1";
			}else {
				long clinica = (long) session.getAttribute("activeCentro");
				
				Long idUsuario2 = (Long) session.getAttribute("idUsuario");
				int user = idUsuario2.intValue();
				
				Clinica clinicaDb = clinicaServiceImpl.findClinicaById(clinica);
				Invitation invitationNew = new Invitation();
				invitationNew.setFecha(new Date());
				invitationNew.setFk_idUsuario(user);
				invitationNew.setFk_idClinica(clinica);
				invitationNew.setPerfil(invitation.getPerfil());
				invitationNew.setEmail(invitation.getEmail());
				invitationNew.setEstado(0);
				
				LOG.info("DATOS INVITACION: "+ invitationNew);
				
				if(null != invitationServiceImpl.addInvitation(invitationNew)) {
					
					//envia correo al invitado
					String message = "Has sido invitado a utilizar Clinic Calendar por "
					+session.getAttribute("username") +" en "+ clinicaDb.getNombreClinica() +
							". Para registrarte haz click aqui: \n" + "https://clinic-calendar.herokuapp.com/user/invitationregister";
					String subject = "Invitación a utilizar Clinic Calendar";
			        mailService.sendMail("clinic-calendar@outlook.com",email_inv,subject,message);
			        
			        return "redirect:/user/invitation?success=1";
				}
			}

		}else {
			return "invitation";
		}

		return "invitation";
	}
	
	@GetMapping("/invitationregister")
	public String invitationRegister(Model model, User user,
			@RequestParam(name="errormail",required = false)String errormail,
			@RequestParam(name="success",required = false)String success) {	
		
		model.addAttribute("success",success);
		model.addAttribute("errormail",errormail);
		return ViewConstant.REGISTERINV;
	}
	
	//registro de usuario desde invitationregister.html
	@PostMapping("/adduserinvitation")
	public String addUserInvitation(@ModelAttribute(name = "user") User user,
			Model model,@ModelAttribute(name="form") MailForm form,
			@RequestParam("email") String email,@RequestParam(name="errormail",required = false)String errormail,
			@RequestParam(name="success",required = false)String success){
		
		User userDb = userService.findUserByEmail(email);
		Invitation invitation = invitationService.findInvitationByEmailAndEstado(email, 1);
		
		LOG.info("resultado: "+userDb);
		if(userDb != null){
			LOG.info("usuario ya existe");
			model.addAttribute("errormail",true);
			return "redirect:/user/invitationregister?errormail";
		}else{
		
			//user.setPass(passwordEncoder.encode(user.getPass()));
			user.setCreated_at(new Date());
			user.setPerfil(invitation.getPerfil());
			if(null != userService.addUser(user)){
				//DEBE PREGUNTAR QUE PERFIL TIENE,SI ES PROFESIONAL DEBE GUARDAR EN LA TABLA DEL MISMO NOMBRE
				if(user.getPerfil() == 0) {
					LOG.info("no es profesional");
					usuarioCentroServiceImpl.addUsuarioCentro(user.getIdusuario(), invitation.getFk_idClinica());
				}else {
					LOG.info("es profesional");
					
					Profesional profesional = new Profesional();
					profesional.setFkIdUsuario(user.getIdusuario());
					profesional.setNombre(user.getNombre().toUpperCase());
					profesional.setA_pat(user.getApat().toUpperCase());
					profesional.setA_mat(user.getAmat().toUpperCase());
					profesional.setEmail(user.getEmail());
					profesional.setCod_centro(invitation.getFk_idClinica());
					profesionalServiceImpl.addProfesional(profesional);
					
					usuarioCentroServiceImpl.addUsuarioCentro(user.getIdusuario(), invitation.getFk_idClinica());
					
					LOG.info("profesional guardado ok");
					
				}
				
				model.addAttribute("result", 1);
		        String message = "Bienvenido a Clinic Calendar, para inciar sesión haz click aqui: \n" + "https://clinic-calendar.herokuapp.com";
				String subject = "Bienvenido a Clinic Calendar";
		        mailService.sendMail("clinic-calendar@outlook.com",email,subject,message);
				
				return "redirect:/user/invitationregister?success=1";
			}else {
				model.addAttribute("result", 0);
			}
		}
		return "register-invitation";
	}

	@GetMapping("/list/{idCentro}")
	public @ResponseBody List<Map<String, Object>> getUsersList(@PathVariable Long idCentro){
		List<Map<String, Object>> users = userService.getListaUsuariosCentro(idCentro);
		return users;
	}
	
	@PutMapping("/delete/{idusuario}")
	public ResponseEntity<?> eliminaUsuario(@PathVariable Long idusuario){
		User userBd = userService.findUserById(idusuario);
		
		if(userBd == null) {
			return ResponseEntity.notFound().build();
		}else {
			userBd.setVigente(0);
			userBd.setUpdated_at(new Date());
			userService.addUser(userBd);
			return ResponseEntity.ok(userBd);
		}
	}
	
	@PutMapping("/{idusuario}")
	public ResponseEntity<?> editaUsuario(@PathVariable Long idusuario){
		User userBd = userService.findUserById(idusuario);
		
		if(userBd == null) {
			return ResponseEntity.notFound().build();
		}else {
			userBd.setPerfil(1);
			userBd.setUpdated_at(new Date());
			userService.addUser(userBd);
			return ResponseEntity.ok(userBd);
		}
	}
	
	@GetMapping("/administration")
	public ModelAndView administration(Invitation invitation, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView("user-admin");
		model.addAttribute("activeUser",session.getAttribute("username"));
		model.addAttribute("activePerfil",session.getAttribute("tipoUser"));
		return mv;
	}
	
	
	@GetMapping("/profesional")
	public String profesional() {
		return "profesional";
	}
	
}
