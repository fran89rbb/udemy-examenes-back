package com.microservicios.app.examenes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.app.examenes.models.entity.Asignatura;
import com.microservicios.app.examenes.models.entity.Examen;
import com.microservicios.app.examenes.models.repository.IAsignaturaRepository;
import com.microservicios.app.examenes.models.repository.IExamenRepository;

@Service
public class ExamenServiceImpl implements IExamenService{
	
	@Autowired
	private IExamenRepository examenRepository;
	
	@Autowired
	private IAsignaturaRepository asignaturaRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Examen> findAll() {
		return examenRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Examen> findById(Long id) {
		return examenRepository.findById(id);
	}

	@Override
	public Examen save(Examen examen) {
		return examenRepository.save(examen);
	}

	@Override
	public void deleteById(Long id) {
		examenRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return examenRepository.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> findAllAsignaturas() {
		return asignaturaRepository.findAll();
	}

}
