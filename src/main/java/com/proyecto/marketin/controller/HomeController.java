package com.proyecto.marketin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomeController {
	
	
	@GetMapping("/login")
	public String Login() {
		return "authentication-signin";

	}
	
	@GetMapping("/index")
	public String home(){
		return "index";
	}
	
	
	
	

}