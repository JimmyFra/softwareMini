package com.proyecto.marketin.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.marketin.model.Empleado;
import com.proyecto.marketin.repository.EmpleadoRepository;
import com.proyecto.marketin.service.AuthService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/auth" )
@RequiredArgsConstructor
public class AuthRestController {
	
	private final AuthService authService;
	private final EmpleadoRepository empleadoRepository;

	@GetMapping("/usuario/{username}")
	public List<Empleado> getEmpleado(@PathVariable String username ) {
		Optional<Empleado> optionalEmpleado = empleadoRepository.findByUsername(username);
	    return optionalEmpleado.map(Collections::singletonList).orElse(Collections.emptyList());
	}
	
	@PostMapping(value = "login")
	public ResponseEntity <AuthResponse> login(@RequestBody LoginRequest request){
		return ResponseEntity.ok(authService.login(request));
	}
 

}
