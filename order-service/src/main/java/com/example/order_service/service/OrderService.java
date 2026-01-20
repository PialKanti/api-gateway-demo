package com.example.order_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderService {

    private final RestClient restClient;

    public OrderService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String processPayment() {
        try {
            return restClient.get()
                    .uri("http://api-gateway:8080/api/payments/pay")
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            return "Payment service not available";
        }
    }
}
