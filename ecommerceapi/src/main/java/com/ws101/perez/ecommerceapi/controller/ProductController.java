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

/**
 * REST Controller for Product API.
 *
 * Handles all HTTP requests for product management including:
 * CRUD operations, filtering, and validation handling.
 *
 * Base URL: /api/v1/products
 *
 * Uses ResponseEntity to control HTTP status codes:
 * - 200 OK for successful requests
 * - 201 Created for POST
 * - 204 No Content for DELETE
 * - 404 Not Found for missing resources
 *
 * @author Mac Andrei Perez
 * @see ProductService
 * @see Product
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    /**
     * Constructor injection of ProductService.
     *
     * @param service service layer for business logic
     */
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Get all products.
     *
     * @return list of products (200 OK)
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Get product by ID.
     *
     * @param id product ID
     * @return product if found (200 OK)
     * @throws ProductNotFoundException if product does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product p = service.getById(id);

        if (p == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        return ResponseEntity.ok(p);
    }

    /**
     * Create new product.
     *
     * @param product product data
     * @return created product (201 Created)
     */
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product created = service.create(product);

        return ResponseEntity
                .status(201)
                .header("Location", "/api/v1/products/" + created.getId())
                .body(created);
    }

    /**
     * Update entire product.
     *
     * @param id product ID
     * @param product new product data
     * @return updated product (200 OK)
     * @throws ProductNotFoundException if product not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id,
                                         @Valid @RequestBody Product product) {

        Product updated = service.update(id, product);

        if (updated == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        return ResponseEntity.ok(updated);
    }

    /**
     * Partially update product.
     *
     * @param id product ID
     * @param product fields to update
     * @return updated product (200 OK)
     * @throws ProductNotFoundException if product not found
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patch(@PathVariable int id,
                                        @RequestBody Product product) {

        Product updated = service.patch(id, product);

        if (updated == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        return ResponseEntity.ok(updated);
    }

    /**
     * Delete product by ID.
     *
     * @param id product ID
     * @return 204 No Content if successful
     * @throws ProductNotFoundException if product not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        if (!service.delete(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Filter products by type and value.
     *
     * @param filterType filter type (category or name)
     * @param filterValue value to search
     * @return filtered list of products (200 OK)
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        return ResponseEntity.ok(service.filter(filterType, filterValue));
    }
}git push origin --delete feature/feature_perez