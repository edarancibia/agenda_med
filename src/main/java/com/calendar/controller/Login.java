package com.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.calendar.entities.User;
import com.calendar.service.UserServiceImpl;

@Controller
public class Login {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserServiceImpl userService;

	/*@GetMapping("/")
	public String loginForm(User user) {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute(name="user") User user,
			@RequestParam("email") String email,
			@RequestParam("password") String pass) {
		
		if(null != userService.findByEmailAndPass(email, pass)) {
			return "calendar";
		}else {
			return "login";
		}
	
	}*/
}
