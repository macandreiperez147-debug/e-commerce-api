package com.ws101.perez.ecommerceapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ws101.perez.ecommerceapi.model.Product;
import com.ws101.perez.ecommerceapi.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:5500")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // PUBLIC - GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // PUBLIC - GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ADMIN ONLY - CREATE PRODUCT
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product created = service.create(product);

        return ResponseEntity
                .status(201)
                .header("Location", "/api/v1/products/" + created.getId())
                .body(created);
    }

    // ADMIN ONLY - UPDATE PRODUCT
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,
                                          @RequestBody Product product) {
        return ResponseEntity.ok(service.update(id, product));
    }

    // ADMIN ONLY - PATCH PRODUCT
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patch(@PathVariable Long id,
                                         @RequestBody Product product) {
        return ResponseEntity.ok(service.patch(id, product));
    }

    // ADMIN ONLY - DELETE PRODUCT (TASK REQUIREMENT)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUBLIC - FILTER PRODUCTS
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        return ResponseEntity.ok(service.filter(filterType, filterValue));
    }
}