package com.microservicios.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class MicorservicioZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicorservicioZuulApplication.class, args);
	}

}
