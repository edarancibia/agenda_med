package com.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarController {

	/*@RequestMapping(value="/", method=RequestMethod.GET) 
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}*/
	
	/*@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/index";
	}*/
}
