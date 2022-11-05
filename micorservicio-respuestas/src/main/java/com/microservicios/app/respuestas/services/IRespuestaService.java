package com.microservicios.app.respuestas.services;

import com.microservicios.app.respuestas.models.entity.Respuesta;

public interface IRespuestaService {
	
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);

}
