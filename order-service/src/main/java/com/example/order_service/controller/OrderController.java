package com.example.order_service.controller;

import com.example.order_service.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String placeOrder() {
        String paymentResponse = orderService.processPayment();
        return "Order processed. Payment response: " + paymentResponse;
    }
}
