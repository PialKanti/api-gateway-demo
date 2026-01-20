package com.example.payment_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/pay")
    public String pay() {
        return "Payment processed by port " + port;
    }
}
