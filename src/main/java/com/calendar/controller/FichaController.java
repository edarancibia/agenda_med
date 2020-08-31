package com.calendar.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.calendar.entities.Ficha;
import com.calendar.impl.FichaServiceImpl;

@RestController
@RequestMapping("/ficha")
public class FichaController {
	
	@Autowired
	public FichaServiceImpl fichaService;
	
	@GetMapping("/")
	public ModelAndView index(HttpSession session, Model model) {
		ModelAndView modelAndView = new ModelAndView("ficha");
		model.addAttribute("activeUser",session.getAttribute("username"));
		return modelAndView;
	}
	
	//SAVE
	@PostMapping("/save")
	public ResponseEntity<?> addFicha(@RequestBody Ficha ficha){
		ficha = fichaService.addFicha(ficha);
		return ResponseEntity.status(HttpStatus.CREATED).body(ficha);
	}
	
	//get by pac
	@GetMapping("get-by-pac/{rutPac}")
	public @ResponseBody List<Ficha> getByPaciente(@PathVariable int rutPac){
		List<Ficha> fichas = fichaService.findFichaByRutPac(rutPac);
		return fichas;
	}

	//get by id
	@GetMapping("/get-by-id{idFicha}")
	public ResponseEntity<?> getById(@PathVariable Long idFicha){
		Ficha ficha = fichaService.findFichaByIdFicha(idFicha);
		
		if(ficha == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Ficha>(ficha,HttpStatus.OK);
		}
	}
}
