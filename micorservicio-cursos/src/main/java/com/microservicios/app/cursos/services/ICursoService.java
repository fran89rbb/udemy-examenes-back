package com.microservicios.app.cursos.services;

import java.util.Optional;

import com.microservicios.app.cursos.models.entity.Curso;


public interface ICursoService {
	
	Iterable<Curso> findAll();
	
	Optional<Curso> findById(Long id);
	
	Curso save(Curso curso);
	
	void deleteById(Long id);

}
