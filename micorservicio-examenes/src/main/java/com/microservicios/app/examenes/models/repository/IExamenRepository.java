package com.microservicios.app.examenes.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.app.examenes.models.entity.Examen;

public interface IExamenRepository extends JpaRepository<Examen, Long>{

}
