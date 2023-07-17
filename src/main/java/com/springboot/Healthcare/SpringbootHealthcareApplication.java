package com.springboot.Healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages= {"com.springboot.Healthcare"})
@ComponentScan(basePackages = "com.springboot.Healthcare")

public class SpringbootHealthcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHealthcareApplication.class, args);
	}

}
