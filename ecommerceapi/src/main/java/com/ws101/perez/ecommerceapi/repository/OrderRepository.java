package com.ws101.perez.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ws101.perez.ecommerceapi.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}