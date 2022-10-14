package com.microservicios.app.alumnos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.app.alumnos.models.entity.Alumno;
import com.microservicios.app.alumnos.models.services.IAlumnoService;

@RestController
public class AlumnoController {
	
	@Autowired
	private IAlumnoService alumnoService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(alumnoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<Alumno> alumno = alumnoService.findById(id);
		
		if(alumno.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(alumno.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno){
		
		Alumno alumnoBd = alumnoService.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoBd);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id){
		
		Optional<Alumno> alumnoDb = alumnoService.findById(id);
		
		if(alumnoDb.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoEditar = alumnoDb.get();
		alumnoEditar.setNombre(alumno.getNombre());
		alumnoEditar.setApellido(alumno.getApellido());
		alumnoEditar.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumnoEditar)); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		alumnoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
