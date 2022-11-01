package com.microservicios.app.examenes.services;

import java.util.List;
import java.util.Optional;

import com.microservicios.app.examenes.models.entity.Examen;


public interface IExamenService {
	
	Iterable<Examen> findAll();
	
	Optional<Examen> findById(Long id);
	
	Examen save(Examen examen);
	
	void deleteById(Long id);
	
	List<Examen> findByNombre(String term);

}
