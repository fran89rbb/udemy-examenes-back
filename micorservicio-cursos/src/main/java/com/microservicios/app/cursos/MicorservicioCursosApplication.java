package com.microservicios.app.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.microservicios.app.alumnos.models.entity",
			"com.microservicios.app.cursos.models.entity",
			"com.microservicios.app.examenes.models.entity"})
public class MicorservicioCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicorservicioCursosApplication.class, args);
	}

}
