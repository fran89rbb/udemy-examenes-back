package com.microservicios.app.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservicios.app.alumnos.models.entity.Alumno;

@FeignClient("microservicio-alumnos")
public interface IAlumnoFeignClient {
	
	@GetMapping("/alumnos-por-curso")
	Iterable<Alumno> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);

}
