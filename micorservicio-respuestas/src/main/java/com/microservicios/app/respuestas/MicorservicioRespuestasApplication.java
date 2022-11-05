package com.microservicios.app.respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.microservicios.app.respuestas.models.entity",
			"com.microservicios.app.alumnos.models.entity",
			"com.microservicios.app.examenes.models.entity"})
public class MicorservicioRespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicorservicioRespuestasApplication.class, args);
	}

}
