package com.example.order_service.controller;

import com.example.order_service.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling order-related HTTP requests.
 *
 * <p>This controller exposes endpoints under the {@code /api/orders} base path
 * for order operations. It delegates business logic to the {@link OrderService}
 * and handles request/response transformation.</p>
 *
 * <p>All endpoints require a valid API key to be passed in the request header,
 * which is forwarded to downstream services for authentication.</p>
 *
 * @author Pial Kanti Samadder
 * @since 1.0
 * @see OrderService
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * Constructs an OrderController with the required dependencies.
     *
     * @param orderService the service handling order business logic
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Places a new order and processes the associated payment.
     *
     * <p>This endpoint initiates the order placement flow by calling the
     * Payment Service through the API Gateway. The API key from the original
     * request is forwarded to maintain authentication across service calls.</p>
     *
     * @param apiKey the API key from the X-API-KEY header for downstream authentication
     * @return a response message containing the order status and payment result
     */
    @GetMapping("/place")
    public String placeOrder(@RequestHeader("X-API-KEY") String apiKey) {
        // Delegate payment processing to the service layer
        String paymentResponse = orderService.processPayment(apiKey);
        return "Order processed. Payment response: " + paymentResponse;
    }
}
