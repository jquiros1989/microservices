package com.ucreativa.microservicios.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ucreativa.microservicios.model.Suma;

public interface SumaRepository extends MongoRepository<Suma, String> {

}
