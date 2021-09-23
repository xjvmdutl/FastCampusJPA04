package com.fastcampus.jpa.FastCampusJPA04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class FastCampusJpa04Application {

	public static void main(String[] args) {
		SpringApplication.run(FastCampusJpa04Application.class, args);
	}

}
