package com.microservicios.app.examenes.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.app.examenes.models.entity.Asignatura;

public interface IAsignaturaRepository extends JpaRepository<Asignatura, Long>{

}
