package com.ws101.perez.ecommerceapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws101.perez.ecommerceapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory_NameIgnoreCase(String name);

    List<Product> findByNameContainingIgnoreCase(String name);
}