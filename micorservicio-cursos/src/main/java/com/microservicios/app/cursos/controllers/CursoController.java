package com.microservicios.app.cursos.controllers;

import java.util.List;
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
import com.microservicios.app.cursos.models.entity.Curso;
import com.microservicios.app.cursos.services.ICursoService;

@RestController
public class CursoController {
	
	@Autowired
	private ICursoService cursoService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(cursoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<Curso> curso = cursoService.findById(id);
		
		if(curso.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(curso.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Curso curso){
		
		Curso cursoBd = cursoService.save(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoBd);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
		
		Optional<Curso> cursoDb = cursoService.findById(id);
		
		if(cursoDb.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoEditar = cursoDb.get();
		cursoEditar.setNombre(curso.getNombre());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoEditar)); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		cursoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	//----------RELACION CON ALUMNOS----------
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
		
		Optional<Curso> cursoOpt = cursoService.findById(id);
		
		if(!cursoOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoDb = cursoOpt.get();
		
		alumnos.forEach(a -> {
			cursoDb.addAlumno(a);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoDb));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
		
		Optional<Curso> cursoOpt = cursoService.findById(id);
		
		if(!cursoOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoDb = cursoOpt.get();
		
		cursoDb.removeAlumno(alumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoDb));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = cursoService.findCursoByAlumnoId(id);
		
		return ResponseEntity.ok(curso);
	}

}
