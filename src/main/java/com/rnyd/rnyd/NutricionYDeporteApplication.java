package com.rnyd.rnyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ComponentScan(basePackages = "com.rnyd.rnyd")
@EntityScan(basePackages = "com.rnyd.rnyd.model")
@SpringBootApplication
public class NutricionYDeporteApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutricionYDeporteApplication.class, args);
	}

}