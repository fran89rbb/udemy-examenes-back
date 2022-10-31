package com.microservicios.app.examenes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.app.examenes.models.entity.Examen;
import com.microservicios.app.examenes.models.repository.IExamenRepository;

@Service
public class ExamenServiceImpl implements IExamenService{
	
	@Autowired
	private IExamenRepository examenRepository;

	@Override
	public Iterable<Examen> findAll() {
		return examenRepository.findAll();
	}

	@Override
	public Optional<Examen> findById(Long id) {
		// TODO Auto-generated method stub
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

}
