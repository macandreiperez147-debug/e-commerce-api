package com.ws101.perez.ecommerceapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws101.perez.ecommerceapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Name(String name);
}