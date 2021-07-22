package com.ucreativa.microserviciosuma.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroserviciosController {

	@GetMapping("/suma/{numero1}/mas/{numero2}")
	public String suma(@PathVariable String numero1, @PathVariable String numero2){
		Integer resultado = Integer.parseInt(numero1) + Integer.parseInt(numero2);
		return Integer.toString(resultado);
	}
	
}
