package com.example.order_service.controller;

import com.example.order_service.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/place")
    public String placeOrder(@RequestHeader("X-API-KEY") String apiKey) {
        String paymentResponse = orderService.processPayment(apiKey);
        return "Order processed. Payment response: " + paymentResponse;
    }
}
