package com.proyecto.marketin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.marketin.service.AuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class AdministradorController {
	
	private final AuthService authService;
	@GetMapping("/register")
	public String register(){
		return "Crear-Usuario";
	}
	@PostMapping(value = "registerPost")
	public String register(@RequestBody RegisterRequest request){
		
		authService.register(request);
		return "redirect:/v1/register";
		
	}
	@GetMapping("/editarUsuario")
	public String editarEmpleado(){
		return "editar-usuario";
	}
}
