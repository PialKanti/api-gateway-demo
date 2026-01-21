package com.example.payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Payment Service microservice.
 *
 * <p>This microservice handles payment processing operations and is accessed
 * by other services through the API Gateway. It registers itself with Eureka
 * for service discovery and load balancing.</p>
 *
 * <p>Multiple instances of this service can be deployed to demonstrate
 * load balancing capabilities of the API Gateway.</p>
 *
 * @author Pial Kanti Samadder
 * @since 1.0
 * @see com.example.payment_service.controller.PaymentController
 */
@SpringBootApplication
public class PaymentServiceApplication {

	/**
	 * Application entry point that bootstraps the Payment Service.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
