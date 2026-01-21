package com.example.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

/**
 * Main entry point for the Order Service microservice.
 *
 * <p>This microservice handles order processing operations and communicates
 * with the Payment Service through the API Gateway. It registers itself
 * with Eureka for service discovery.</p>
 *
 * <p>The service exposes REST endpoints for order operations and uses
 * {@link RestClient} for synchronous HTTP communication with other services.</p>
 *
 * @author Pial Kanti Samadder
 * @since 1.0
 * @see com.example.order_service.controller.OrderController
 * @see com.example.order_service.service.OrderService
 */
@SpringBootApplication
public class OrderServiceApplication {

    /**
     * Application entry point that bootstraps the Order Service.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    /**
     * Creates and configures a {@link RestClient} bean for inter-service communication.
     *
     * <p>This RestClient is used by the service layer to make HTTP calls to other
     * microservices through the API Gateway. The builder is auto-configured by
     * Spring Boot with sensible defaults.</p>
     *
     * @param builder the auto-configured RestClient builder
     * @return a configured RestClient instance
     */
    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }
}
