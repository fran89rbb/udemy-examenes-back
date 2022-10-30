package com.microservicios.app.alumnos.models.services;

import java.util.List;
import java.util.Optional;

import com.microservicios.app.alumnos.models.entity.Alumno;

public interface IAlumnoService {
	
	Iterable<Alumno> findAll();
	
	Optional<Alumno> findById(Long id);
	
	Alumno save(Alumno alumno);
	
	void deleteById(Long id);
	
	List<Alumno> findByNombreOrApellido(String term);

}
