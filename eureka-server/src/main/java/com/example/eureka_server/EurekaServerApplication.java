package com.example.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main entry point for the Eureka Server application.
 *
 * <p>This application provides service discovery and registration for the
 * microservices architecture. All microservices (API Gateway, Order Service,
 * Payment Service) register themselves with this server to enable dynamic
 * service discovery and load balancing.</p>
 *
 * <p>The {@link EnableEurekaServer} annotation activates the Netflix Eureka
 * server functionality. This server runs in standalone mode and does not
 * register itself with other Eureka instances.</p>
 *
 * <p>The Eureka dashboard is available at {@code http://localhost:8761} by default.</p>
 *
 * @author Pial Kanti Samadder
 * @since 1.0
 * @see EnableEurekaServer
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	/**
	 * Application entry point that bootstraps the Eureka Server.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
