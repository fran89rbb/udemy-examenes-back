package com.microservicios.app.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.app.alumnos.models.entity.Alumno;
import com.microservicios.app.cursos.models.entity.Curso;
import com.microservicios.app.cursos.models.entity.CursoAlumno;
import com.microservicios.app.cursos.services.ICursoService;
import com.microservicios.app.examenes.models.entity.Examen;

@RestController
public class CursoController {
	
	@Autowired
	private ICursoService cursoService;
	
	/*@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(cursoService.findAll());
	}*/
	
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Curso> cursos = ((List<Curso>) cursoService.findAll()).stream().map(c -> {
			c.getCursoAlumnos().forEach(ca -> {
				Alumno alumno = new Alumno();
				alumno.setId(ca.getAlumnoId());
				c.addAlumno(alumno);
			});
			return c;
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(cursos);
	}
	
	@GetMapping("/pagina")
	public ResponseEntity<?> listar(Pageable pageable){
		return ResponseEntity.ok().body(cursoService.findAll(pageable));
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
	public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Curso cursoBd = cursoService.save(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoBd);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
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
			CursoAlumno cursoAlumno = new CursoAlumno();
			cursoAlumno.setAlumnoId(a.getId());
			cursoAlumno.setCurso(cursoDb);
			cursoDb.addCursoAlumnos(cursoAlumno);
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
		
		CursoAlumno cursoAlumno = new CursoAlumno();
		cursoAlumno.setAlumnoId(alumno.getId());
		cursoDb.removeCursoAlumnos(cursoAlumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoDb));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = cursoService.findCursoByAlumnoId(id);
		
		if(curso != null) {
			List<Long> examenesId = (List<Long>) cursoService.obtenerExamenesIdsConRespuestaByAlumno(id);
			
			List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
				if(examenesId.contains(examen.getId())){
					examen.setRespondido(true);
				}
				return examen;
			}).collect(Collectors.toList());
			
			curso.setExamenes(examenes);
		}
		
		return ResponseEntity.ok(curso);
	}
	
	
	//----------RELACION CON EXAMENES----------
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
		Optional<Curso> cursoDb = cursoService.findById(id);
		
		if(!cursoDb.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoUpdate = cursoDb.get();
		examenes.forEach(e -> cursoUpdate.addExamen(e));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoUpdate));
	}

	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Curso> cursoDb = cursoService.findById(id);
		
		if(!cursoDb.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoUpdate = cursoDb.get();
		cursoUpdate.removeExamen(examen);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoUpdate));
	}
	
	public ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " +err.getField()+ " " +err.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errores);
	}
}
