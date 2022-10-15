package com.microservicios.app.cursos.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.app.cursos.models.entity.Curso;

public interface ICursoRepository extends JpaRepository<Curso, Long>{

}
