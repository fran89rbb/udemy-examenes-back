package com.microservicios.app.cursos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.app.cursos.models.entity.Curso;
import com.microservicios.app.cursos.models.repository.ICursoRepository;

@Service
public class CursoServiceImpl implements ICursoService{
	
	@Autowired
	private ICursoRepository cursoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Curso> findAll() {
		return cursoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> findById(Long id) {
		return cursoRepository.findById(id);
	}

	@Override
	public Curso save(Curso curso) {
		return cursoRepository.save(curso);
	}

	@Override
	public void deleteById(Long id) {
		cursoRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return cursoRepository.findCursoByAlumnoId(id);
	}

}
