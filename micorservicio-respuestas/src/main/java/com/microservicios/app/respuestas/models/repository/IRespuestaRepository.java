package com.microservicios.app.respuestas.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.app.respuestas.models.entity.Respuesta;

public interface IRespuestaRepository extends JpaRepository<Respuesta, Long>{

}
