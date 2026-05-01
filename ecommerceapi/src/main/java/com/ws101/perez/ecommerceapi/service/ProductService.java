package com.ws101.perez.ecommerceapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ws101.perez.ecommerceapi.model.Category;
import com.ws101.perez.ecommerceapi.model.Product;
import com.ws101.perez.ecommerceapi.repository.CategoryRepository;
import com.ws101.perez.ecommerceapi.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // GET ALL
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // GET BY ID
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    // CREATE (FIXED - IMPORTANT PART)
    public Product create(Product product) {

        Long categoryId = product.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        product.setCategory(category);

        return productRepository.save(product);
    }

    // UPDATE (FULL REPLACE)
    public Product update(Long id, Product newProduct) {

        Product existing = getById(id);

        newProduct.setId(existing.getId());

        return productRepository.save(newProduct);
    }

    // PATCH (SAFE VERSION)
    public Product patch(Long id, Product update) {

        Product existing = getById(id);

        if (update.getName() != null)
            existing.setName(update.getName());

        if (update.getDescription() != null)
            existing.setDescription(update.getDescription());

        if (update.getImageUrl() != null)
            existing.setImageUrl(update.getImageUrl());

        if (update.getCategory() != null && update.getCategory().getId() != null) {
            Category category = categoryRepository.findById(update.getCategory().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
            existing.setCategory(category);
        }

        if (update.getPrice() > 0)
            existing.setPrice(update.getPrice());

        if (update.getStock() >= 0)
            existing.setStock(update.getStock());

        return productRepository.save(existing);
    }

    // DELETE
    public void delete(Long id) {

        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }

        productRepository.deleteById(id);
    }

    // FILTER
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