package com.ApiVuelos.ApiVuelos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "com.utn.tssi.tp5.Models.model")
public class ApiVuelosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVuelosApplication.class, args);
	}
}
