package com.microservicios.app.examenes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.microservicios.app.examenes.models.entity.Examen;
import com.microservicios.app.examenes.models.entity.Pregunta;
import com.microservicios.app.examenes.services.IExamenService;

@RestController
public class ExamenController {
	
	@Autowired
	private IExamenService examenService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(examenService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<Examen> examen = examenService.findById(id);
		
		if(examen.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(examen.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody Examen examen, BindingResult result){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Examen examenBd = examenService.save(examen);
		return ResponseEntity.status(HttpStatus.CREATED).body(examenBd);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		examenService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Examen> examenOpt = examenService.findById(id);
		
		if(!examenOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Examen examenDb = examenOpt.get();
		examenDb.setNombre(examen.getNombre());	
		
		List<Pregunta> eliminadas = examenDb.getPreguntas()
		.stream()
		.filter(pbd -> !examen.getPreguntas().contains(pbd))
		.collect(Collectors.toList());
		
		eliminadas.forEach(examenDb::removePregunta);
		
		examenDb.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(examenService.save(examenDb));
	}
	
	@GetMapping("filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(examenService.findByNombre(term));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.ok().body(examenService.findAllAsignaturas());
	}
	
	public ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " +err.getField()+ " " +err.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errores);
	}
}
