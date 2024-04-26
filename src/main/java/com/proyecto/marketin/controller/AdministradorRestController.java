package com.proyecto.marketin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.marketin.model.Empleado;
import com.proyecto.marketin.model.Perfil;
import com.proyecto.marketin.repository.EmpleadoRepository;
import com.proyecto.marketin.repository.PerfilRepository;
import com.proyecto.marketin.service.AgregarPerfilEmpleadoRequest;
import com.proyecto.marketin.service.AuthService;
import com.proyecto.marketin.service.RegisterPerfilRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class AdministradorRestController {
	private final AuthService authService;
	private final EmpleadoRepository empleadoRepository;
	private final PerfilRepository perfilRepository;

	@GetMapping(value = "usuarios")
	public List<Empleado> getAllEmpleados() {
		return empleadoRepository.findAll();
	}

	@GetMapping(value = "perfiles")
	public List<Perfil> getAllPerfiles() {
		return perfilRepository.findAll();
	}

	@PostMapping(value = "agregarPerfil")
	public void setPerfil(@RequestBody RegisterPerfilRequest request) {

		authService.registerPerfil(request);
	}
	
	
	@PostMapping("/agregarPerfilEmpleado")
	public void setPerfilEmpleado(@RequestBody AgregarPerfilEmpleadoRequest request) {

		authService.agregarPerfilAEmpleado(request);
	}
	
}
