package com.ucreativa.microserviciosuma.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import com.ucreativa.microservicios.dao.SumaRepository;
import com.ucreativa.microservicios.model.Suma;

@EnableMongoRepositories(basePackages = { "com.ucreativa.microservicios.dao" })
@Service
public class MongoStoreService {

	@Autowired
	private SumaRepository repository;
	
	public void saveIntoRepository(String operacion, String resultado) {
		Suma sumaToSave = new Suma(new Date(), operacion, resultado);
		repository.save(sumaToSave);
	}

	public List<Suma> getAllSumas() {
		return repository.findAll();
	}
}
