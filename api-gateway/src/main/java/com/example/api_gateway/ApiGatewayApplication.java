package com.example.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the API Gateway application.
 *
 * <p>This Spring Cloud Gateway application serves as the central entry point for all
 * microservices in the system. It provides:</p>
 * <ul>
 *   <li>Request routing to downstream services (order-service, payment-service)</li>
 *   <li>API key authentication via {@link com.example.api_gateway.config.ApiKeyFilter}</li>
 *   <li>Rate limiting based on client IP address</li>
 *   <li>Service discovery integration with Eureka</li>
 * </ul>
 *
 * @author Pial Kanti Samadder
 * @since 1.0
 * @see com.example.api_gateway.config.ApiKeyFilter
 * @see com.example.api_gateway.config.IpKeyResolver
 */
@SpringBootApplication
public class ApiGatewayApplication {

	/**
	 * Application entry point that bootstraps the Spring Cloud Gateway.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
