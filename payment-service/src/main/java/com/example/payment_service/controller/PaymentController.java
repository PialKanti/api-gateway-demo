package com.example.payment_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling payment-related HTTP requests.
 *
 * <p>This controller exposes endpoints under the {@code /api/payments} base path
 * for payment operations. The server port is included in responses to demonstrate
 * load balancing across multiple service instances.</p>
 *
 * <p>In a production environment, this controller would integrate with payment
 * gateways and handle transaction processing logic.</p>
 *
 * @author Pial Kanti Samadder
 * @since 1.0
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    /** Server port injected from configuration, used to identify the instance handling the request. */
    @Value("${server.port}")
    private String port;

    /**
     * Processes a payment request and returns a confirmation message.
     *
     * <p>The response includes the server port to demonstrate which instance
     * handled the request when multiple instances are deployed behind the
     * API Gateway's load balancer.</p>
     *
     * @return a confirmation message including the port of the handling instance
     */
    @GetMapping("/pay")
    public String pay() {
        // Include port in response to verify load balancing across instances
        return "Payment processed by port " + port;
    }
}
