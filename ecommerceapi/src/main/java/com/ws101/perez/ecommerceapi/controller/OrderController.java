package com.ws101.perez.ecommerceapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws101.perez.ecommerceapi.model.Order;
import com.ws101.perez.ecommerceapi.repository.OrderRepository;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    // GET all orders
    @GetMapping
    public List<Order> getOrders() {
        return repository.findAll();
    }

    // CREATE order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return repository.save(order);
    }
}