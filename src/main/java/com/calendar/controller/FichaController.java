package com.calendar.controller;

import java.util.List;
import java.util.Map;

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
import com.calendar.impl.FichaServiceImpl;
import com.calendar.impl.ProfesionalServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/ficha")
@SessionAttributes({"activeUser","centro","activeIdUser","activePerfil","activeProf","activeCentro"})
public class FichaController {
	
	@Autowired
	public FichaServiceImpl fichaService;
	
	@GetMapping("/{rut_num}/{idEvento}")
	public ModelAndView index(HttpSession session, Model model, @PathVariable int rut_num,@PathVariable int idEvento) {
		ModelAndView modelAndView = new ModelAndView("ficha");
		model.addAttribute("activeUser",session.getAttribute("username"));
		model.addAttribute("activePerfil",session.getAttribute("tipoUser"));
		model.addAttribute("activeProf",session.getAttribute("idProfesional"));
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
}
