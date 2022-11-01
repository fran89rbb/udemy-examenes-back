package com.microservicios.app.examenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.microservicios.app.examenes.models.entity"})
public class MicorservicioExamenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicorservicioExamenesApplication.class, args);
	}

}
