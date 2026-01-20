package com.example.order_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderService {

    private final RestClient restClient;
    private final String gatewayUrl;

    public OrderService(RestClient restClient, @Value("${gateway.url}") String gatewayUrl) {
        this.restClient = restClient;
        this.gatewayUrl = gatewayUrl;
    }

    public String processPayment(String apiKey) {
        try {
            return restClient.get()
                    .uri(gatewayUrl + "/api/payments/pay")
                    .header("X-API-KEY", apiKey)
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            return "Payment service not available";
        }
    }
}
