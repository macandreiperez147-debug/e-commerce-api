package com.ws101.perez.ecommerceapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ws101.perez.ecommerceapi.model.Product;
import com.ws101.perez.ecommerceapi.repository.ProductRepository;

/**
 * Service class for product-related operations.
 *
 * Uses Spring Data JPA to interact with the database instead of in-memory storage.
 *
 * @author Mac Andrei Perez
 * Collaborator: Angelica Naza
 * @since 2026
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from database.
     */
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by ID.
     */
    public Product getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    /**
     * Creates a new product.
     */
    public Product create(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product.
     */
    public Product update(Integer id, Product newProduct) {
        Product existing = getById(id);

        newProduct.setId(existing.getId());
        return productRepository.save(newProduct);
    }

    /**
     * Partially updates a product.
     */
    public Product patch(Integer id, Product update) {
        Product existing = getById(id);

        if (update.getName() != null) existing.setName(update.getName());
        if (update.getDescription() != null) existing.setDescription(update.getDescription());
        if (update.getImageUrl() != null) existing.setImageUrl(update.getImageUrl());
        if (update.getPrice() != 0) existing.setPrice(update.getPrice());
        if (update.getStock() != 0) existing.setStock(update.getStock());
        if (update.getCategory() != null) existing.setCategory(update.getCategory());

        return productRepository.save(existing);
    }

    /**
     * Deletes a product by ID.
     */
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}