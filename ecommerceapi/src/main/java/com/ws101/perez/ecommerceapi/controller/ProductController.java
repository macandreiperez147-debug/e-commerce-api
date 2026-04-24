package com.ws101.perez.ecommerceapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

import com.ws101.perez.ecommerceapi.exception.ProductNotFoundException;
import com.ws101.perez.ecommerceapi.model.Product;
import com.ws101.perez.ecommerceapi.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product p = service.getById(id);

        if (p == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        return ResponseEntity.ok(p);
    }

    // CREATE product
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product created = service.create(product);

        return ResponseEntity
                .status(201)
                .header("Location", "/api/v1/products/" + created.getId())
                .body(created);
    }

    // UPDATE product
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id,
                                         @Valid @RequestBody Product product) {

        Product updated = service.update(id, product);

        if (updated == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        return ResponseEntity.ok(updated);
    }

    // PATCH product
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patch(@PathVariable int id,
                                        @RequestBody Product product) {

        Product updated = service.patch(id, product);

        if (updated == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        return ResponseEntity.ok(updated);
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        if (!service.delete(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        return ResponseEntity.noContent().build();
    }

    // FILTER products
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        return ResponseEntity.ok(service.filter(filterType, filterValue));
    }
}