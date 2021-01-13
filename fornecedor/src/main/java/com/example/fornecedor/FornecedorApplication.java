package com.example.fornecedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FornecedorApplication {

	public static void main(String[] args) {
		System.setProperty("spring.cloud.bootstrap.enabled","true");
		SpringApplication.run(FornecedorApplication.class, args);
	}

}
