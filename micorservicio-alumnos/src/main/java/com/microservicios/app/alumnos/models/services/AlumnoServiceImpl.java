package com.microservicios.app.alumnos.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.app.alumnos.client.ICursoFeignClient;
import com.microservicios.app.alumnos.models.entity.Alumno;
import com.microservicios.app.alumnos.models.repository.IAlumnoRepository;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
	
	@Autowired
	private IAlumnoRepository alumnoRepository;
	
	@Autowired
	private ICursoFeignClient clientCurso;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		return alumnoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		return alumnoRepository.findById(id);
	}

	@Override
	public Alumno save(Alumno alumno) {
		return alumnoRepository.save(alumno);
	}

	@Override
	public void deleteById(Long id) {
		alumnoRepository.deleteById(id);
		this.eliminarCursoAlumnoPorId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String term) {
		return alumnoRepository.findByNombreOrApellido(term);
	}

	@Override
	public Page<Alumno> findAll(Pageable pageable) {
		return alumnoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAllById(Iterable<Long> ids) {
		return alumnoRepository.findAllById(ids);
	}

	@Override
	public void eliminarCursoAlumnoPorId(Long id) {
		clientCurso.eliminarCursoAlumnoPorId(id);
	}

}
