package com.example.order_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * Service class containing business logic for order operations.
 *
 * <p>This service handles order processing and coordinates with other
 * microservices through the API Gateway. It uses {@link RestClient} for
 * synchronous HTTP communication.</p>
 *
 * <p>Inter-service calls are routed through the API Gateway to leverage
 * centralized authentication, rate limiting, and load balancing.</p>
 *
 * @author Pial Kanti Samadder
 * @since 1.0
 * @see RestClient
 */
@Service
public class OrderService {

    /** HTTP client for making requests to other microservices. */
    private final RestClient restClient;

    /** Base URL of the API Gateway, configured via application properties. */
    private final String gatewayUrl;

    /**
     * Constructs an OrderService with the required dependencies.
     *
     * @param restClient the HTTP client for inter-service communication
     * @param gatewayUrl the API Gateway base URL from {@code gateway.url} property
     */
    public OrderService(RestClient restClient, @Value("${gateway.url}") String gatewayUrl) {
        this.restClient = restClient;
        this.gatewayUrl = gatewayUrl;
    }

    /**
     * Processes payment for an order by calling the Payment Service.
     *
     * <p>This method makes a synchronous HTTP GET request to the Payment Service
     * through the API Gateway. The API key is forwarded in the request header
     * to authenticate with the gateway.</p>
     *
     * <p>If the Payment Service is unavailable or returns an error, a fallback
     * message is returned instead of propagating the exception.</p>
     *
     * @param apiKey the API key to include in the request header for gateway authentication
     * @return the payment confirmation message, or an error message if the service is unavailable
     */
    public String processPayment(String apiKey) {
        try {
            // Build and execute the request to Payment Service via the API Gateway
            return restClient.get()
                    .uri(gatewayUrl + "/api/payments/pay")
                    .header("X-API-KEY", apiKey)
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            // Return fallback message if payment service call fails
            return "Payment service not available";
        }
    }
}
