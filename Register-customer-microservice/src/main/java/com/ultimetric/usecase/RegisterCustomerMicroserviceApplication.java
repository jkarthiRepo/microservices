package com.ultimetric.usecase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RegisterCustomerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterCustomerMicroserviceApplication.class, args);
	}

}
