package com.ws101.perez.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws101.perez.ecommerceapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}