package com.ucreativa.microserviciosuma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ucreativa.microservicios.model.Suma;
import com.ucreativa.microserviciosuma.services.FileStoreService;
import com.ucreativa.microserviciosuma.services.MongoStoreService;

@RestController
public class MicroserviciosController {
	
	@Autowired
	private FileStoreService fileStoreService;
	
	@Autowired
	private MongoStoreService mongoService;
	
	
	@GetMapping("/sumas/")
	public List<Suma> getSumas(){
		return mongoService.getAllSumas();
	}
	
	@GetMapping("/suma/{numero1}/mas/{numero2}")
	public String suma(@PathVariable String numero1, @PathVariable String numero2){
		Integer resultado = Integer.parseInt(numero1) + Integer.parseInt(numero2);
		fileStoreService.saveFile(Integer.toString(resultado));
		String operacion=numero1+" + "+numero2;
		mongoService.saveIntoRepository(operacion, Integer.toString(resultado));
		return Integer.toString(resultado);
	}
	
}
