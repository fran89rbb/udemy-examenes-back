package com.microservicios.app.cursos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.app.alumnos.models.entity.Alumno;
import com.microservicios.app.cursos.clients.IAlumnoFeignClient;
import com.microservicios.app.cursos.clients.IRespuestaFeignClients;
import com.microservicios.app.cursos.models.entity.Curso;
import com.microservicios.app.cursos.models.repository.ICursoRepository;

@Service
public class CursoServiceImpl implements ICursoService{
	
	@Autowired
	private ICursoRepository cursoRepository;

	@Autowired
	private IRespuestaFeignClients client;
	
	@Autowired
	private IAlumnoFeignClient clientAlumno;
	
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

	@Override
	public Page<Curso> findAll(Pageable pageable) {
		return cursoRepository.findAll(pageable);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestaByAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestaByAlumno(alumnoId);
	}

	@Override
	public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids) {
		return clientAlumno.obtenerAlumnosPorCurso(ids);
	}

}
