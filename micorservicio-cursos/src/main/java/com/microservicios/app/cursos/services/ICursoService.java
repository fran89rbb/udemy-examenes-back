package com.microservicios.app.cursos.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservicios.app.alumnos.models.entity.Alumno;
import com.microservicios.app.cursos.models.entity.Curso;


public interface ICursoService {
	
	Iterable<Curso> findAll();
	
	Page<Curso> findAll(Pageable pageable);
	
	Optional<Curso> findById(Long id);
	
	Curso save(Curso curso);
	
	void deleteById(Long id);
	
	Curso findCursoByAlumnoId(Long id);
	
	Iterable<Long> obtenerExamenesIdsConRespuestaByAlumno(Long alumnoId);
	
	Iterable<Alumno> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);

}
