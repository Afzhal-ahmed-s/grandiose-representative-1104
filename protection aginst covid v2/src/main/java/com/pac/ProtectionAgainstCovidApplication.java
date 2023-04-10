package com.pac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ProtectionAgainstCovidApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtectionAgainstCovidApplication.class, args);
		System.out.println("Up and running!");
	}

}

