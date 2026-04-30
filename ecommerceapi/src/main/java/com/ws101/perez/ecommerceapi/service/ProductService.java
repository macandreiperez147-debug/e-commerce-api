package com.ws101.perez.ecommerceapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ws101.perez.ecommerceapi.model.Product;
import com.ws101.perez.ecommerceapi.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Service layer for Product using Spring Data JPA.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get all products from database.
     */
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * Get product by ID.
     * Throws 404 if not found.
     */
    public Product getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    /**
     * Create new product.
     */
    public Product create(Product product) {
        return productRepository.save(product);
    }

    /**
     * Update entire product.
     */
    public Product update(Integer id, Product newProduct) {
        Product existing = getById(id);
        newProduct.setId(existing.getId());
        return productRepository.save(newProduct);
    }

    /**
     * Partial update (PATCH).
     */
    public Product patch(Integer id, Product update) {
        Product existing = getById(id);

        if (update.getName() != null) existing.setName(update.getName());
        if (update.getDescription() != null) existing.setDescription(update.getDescription());
        if (update.getImageUrl() != null) existing.setImageUrl(update.getImageUrl());
        if (update.getCategory() != null) existing.setCategory(update.getCategory());

        // NOTE: primitive fields cannot be null, so 0 is treated as "ignore"
        if (update.getPrice() != 0) existing.setPrice(update.getPrice());
        if (update.getStock() != 0) existing.setStock(update.getStock());

        return productRepository.save(existing);
    }

    /**
     * Delete product by ID.
     */
    public void delete(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    /**
     * 
     * Filter products using database queries.
     */
    public List<Product> filter(String type, String value) {

        switch (type.toLowerCase()) {

            case "category":
                return productRepository.findByCategory_NameIgnoreCase(value);

            case "name":
                return productRepository.findByNameContainingIgnoreCase(value);

            default:
                throw new IllegalArgumentException("Invalid filter type: " + type);
        }
    }
}