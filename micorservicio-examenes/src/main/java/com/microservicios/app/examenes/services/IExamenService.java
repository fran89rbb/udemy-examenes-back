package com.microservicios.app.examenes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.microservicios.app.examenes.models.entity.Asignatura;
import com.microservicios.app.examenes.models.entity.Examen;


public interface IExamenService {
	
	Iterable<Examen> findAll();
	
	Page<Examen> findAll(Pageable pageable);
	
	Optional<Examen> findById(Long id);
	
	Examen save(Examen examen);
	
	void deleteById(Long id);
	
	List<Examen> findByNombre(String term);
	
	List<Asignatura> findAllAsignaturas();

}
