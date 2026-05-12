package com.ws101.perez.ecommerceapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    // LAB REQUIREMENT: authenticated users only
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createOrder() {
        return ResponseEntity.ok("Order created successfully");
    }

    // optional (still protected unless permitted elsewhere)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getOrders() {
        return ResponseEntity.ok("List of orders (placeholder)");
    }
}