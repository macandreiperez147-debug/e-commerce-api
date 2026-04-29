package com.ws101.perez.ecommerceapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ws101.perez.ecommerceapi.model.Product;
import com.ws101.perez.ecommerceapi.repository.ProductRepository;

/**
 * Service layer for Product (JPA version).
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Integer id, Product newProduct) {
        Product existing = getById(id);

        newProduct.setId(existing.getId());
        return productRepository.save(newProduct);
    }

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

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    /**
     * REQUIRED for controller filtering
     */
    public List<Product> filter(String type, String value) {

        switch (type.toLowerCase()) {

            case "category":
                return productRepository.findByCategory_NameIgnoreCase(value);

            case "name":
                return productRepository.findByNameContainingIgnoreCase(value);

            default:
                throw new RuntimeException("Invalid filter type: " + type);
        }
    }
}