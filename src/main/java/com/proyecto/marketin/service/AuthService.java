package com.proyecto.marketin.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.proyecto.marketin.model.Empleado;
import com.proyecto.marketin.model.Perfil;
import com.proyecto.marketin.repository.EmpleadoRepository;
import com.proyecto.marketin.repository.PerfilRepository;
import com.proyecto.marketin.controller.AuthResponse;
import com.proyecto.marketin.controller.LoginRequest;
import com.proyecto.marketin.controller.RegisterRequest;



@Service

public class AuthService {	
	
	
	private final EmpleadoRepository empleadoRepository;
	private final PerfilRepository perfilRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	public AuthService(EmpleadoRepository empleadoRepository, PerfilRepository perfilRepository, JwtService jwtService,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		
		this.empleadoRepository = empleadoRepository;
		this.perfilRepository = perfilRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		Empleado empleado = empleadoRepository.findByUsername(request.getUsername()).orElseThrow();
		AuthResponse authResponse = new AuthResponse();
	    authResponse.setToken(jwtService.getToken(empleado));
	    return authResponse;

	}

	public void register(RegisterRequest request) {

		Empleado empleado = new Empleado();
		if (empleadoRepository.existsByUsername(request.getUsername())) {	
            throw new RuntimeException("Error: El empleado con este DNI ya está registrado.");
        }
		Set<Perfil> perfiles = new HashSet<>();
		Optional<Perfil> perfilOptional = perfilRepository.findById(request.getPerfil());
		if (perfilOptional.isPresent()) {
		    Perfil perfilEncontrado = perfilOptional.get();
		    perfiles.add(perfilEncontrado);
		} else {
		    throw new RuntimeException("Error: El perfil no fue encontrado");
		}
				empleado.setUsername(request.getUsername());
				empleado.setPassword(passwordEncoder.encode(request.getPassword()));
				empleado.setFirstname(request.getFirstname());
				empleado.setLastname(request.getLastname());
				empleado.setEmail(request.getEmail());
				empleado.setAddress(request.getAddress());
				empleado.setNumberphone(request.getNumberphone());
			    empleado.setPerfiles(perfiles);

				
		empleadoRepository.save(empleado);
		
				
	}
	
	public void registerPerfil(RegisterPerfilRequest request) {
		Perfil perfil = new Perfil();
		perfil.setNombre(request.getPerfil());
		perfilRepository.save(perfil);
			
	}

	public void agregarPerfilAEmpleado(AgregarPerfilEmpleadoRequest request) {
        Empleado empleado = empleadoRepository.findByUsername(request.getUsername()).orElse(null);
        if (empleado != null) {
        	Set<Perfil> perfilActual = empleado.getPerfiles();
        	Set<Perfil> perfilesNuevos = new HashSet<>();
    		Optional<Perfil> perfilOptional = perfilRepository.findByNombre(request.getPerfil());
    		if (perfilOptional.isPresent()) {
    		    Perfil perfilEncontrado = perfilOptional.get();
    		    perfilesNuevos.add(perfilEncontrado);
    		} else {
    		    throw new RuntimeException("Error: El perfil no fue encontrado");
    		}
            if (perfilesNuevos != null) {
            	perfilActual.addAll(perfilesNuevos);
                empleado.setPerfiles(perfilActual);
                empleadoRepository.save(empleado);
            } 
        } 
    }

	
	

}
