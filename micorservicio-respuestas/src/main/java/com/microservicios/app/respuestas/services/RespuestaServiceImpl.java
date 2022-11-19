package com.microservicios.app.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.app.respuestas.models.entity.Respuesta;
import com.microservicios.app.respuestas.models.repository.IRespuestaRepository;

@Service
public class RespuestaServiceImpl implements IRespuestaService {
	
	@Autowired
	private IRespuestaRepository respuestaRepository;

	@Override
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return respuestaRepository.saveAll(respuestas);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findByByAlumnoByExamen(Long alumnoId, Long examenId) {
		return respuestaRepository.findByByAlumnoByExamen(alumnoId, examenId);
	}

	@Override
	public Iterable<Long> findExamenesIdsConRespuestaByAlumno(Long alumnoId) {
		return respuestaRepository.findExamenesIdsConRespuestaByAlumno(alumnoId);
	}

}
