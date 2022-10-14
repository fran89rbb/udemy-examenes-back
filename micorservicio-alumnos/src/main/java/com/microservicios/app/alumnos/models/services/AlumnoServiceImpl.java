package com.microservicios.app.alumnos.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.app.alumnos.models.entity.Alumno;
import com.microservicios.app.alumnos.models.repository.IAlumnoRepository;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
	
	@Autowired
	private IAlumnoRepository alumnoRepository;

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

	}

}
