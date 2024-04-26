package com.proyecto.marketin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v2")
@RequiredArgsConstructor
public class CajeroRestController {
	
	@GetMapping(value = "cajero")
	public String Cajero() {
		return "Todas las funcionalidades del Cajero";
	}
}

