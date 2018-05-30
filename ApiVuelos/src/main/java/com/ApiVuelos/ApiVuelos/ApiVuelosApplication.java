package com.ApiVuelos.ApiVuelos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/")
@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "com.utn.tssi.tp5.Models.model")
public class ApiVuelosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVuelosApplication.class, args);
	}

	@RequestMapping(value = "index.html")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("/index");
		return modelAndView;
	}
}
