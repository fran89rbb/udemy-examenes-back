package com.microservicios.app.alumnos.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.microservicios.app.alumnos.models.entity.Alumno;

public interface IAlumnoService {
	
	Iterable<Alumno> findAll();
	
	Page<Alumno> findAll(Pageable pageable);
	
	Optional<Alumno> findById(Long id);
	
	Alumno save(Alumno alumno);
	
	void deleteById(Long id);
	
	List<Alumno> findByNombreOrApellido(String term);
	
	Iterable<Alumno> findAllById(Iterable<Long> ids);

}
