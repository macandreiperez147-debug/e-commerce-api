package com.ws101.perez.ecommerceapi.service;

import com.ws101.perez.ecommerceapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing products.
 *
 * In-memory storage approach:
 * - Uses ArrayList<Product> as a temporary data store.
 * - Acts like a database but stored only in memory.
 * - Initialized with 10 sample products.
 *
 * Limitations:
 * - Data is NOT persistent (lost when app stops).
 * - No real database is used.
 *
 * This class handles all business logic and CRUD operations.
 */
@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private int nextId = 1;

    /**
     * Constructor initializes sample product data.
     */
    public ProductService() {
        for (int i = 1; i <= 10; i++) {
            productList.add(new Product(
                    nextId++,
                    "Product " + i,
                    "Sample description " + i,
                    100 * i,
                    "Category " + (i % 3),
                    10 * i,
                    ""
            ));
        }
    }

    /**
     * Retrieve all products.
     * @return list of all products
     */
    public List<Product> getAll() {
        return productList;
    }

    /**
     * Find product by ID.
     * @param id product ID
     * @return product if found, otherwise null
     */
    public Product getById(int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Create a new product.
     * @param product product data
     * @return created product
     */
    public Product create(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    /**
     * Replace entire product (PUT).
     * @param id product ID
     * @param newProduct updated product data
     * @return updated product or null if not found
     */
    public Product update(int id, Product newProduct) {
        Product existing = getById(id);
        if (existing == null) return null;

        newProduct.setId(id);
        productList.remove(existing);
        productList.add(newProduct);
        return newProduct;
    }

    /**
     * Partially update product (PATCH).
     * @param id product ID
     * @param update fields to update
     * @return updated product or null if not found
     */
    public Product patch(int id, Product update) {
        Product existing = getById(id);
        if (existing == null) return null;

        if (update.getName() != null) existing.setName(update.getName());
        if (update.getDescription() != null) existing.setDescription(update.getDescription());
        if (update.getPrice() != 0) existing.setPrice(update.getPrice());
        if (update.getCategory() != null) existing.setCategory(update.getCategory());
        if (update.getStockQuantity() != 0) existing.setStockQuantity(update.getStockQuantity());
        if (update.getImageUrl() != null) existing.setImageUrl(update.getImageUrl());

        return existing;
    }

    /**
     * Delete a product by ID.
     * @param id product ID
     * @return true if deleted, false if not found
     */
    public boolean delete(int id) {
        return productList.removeIf(p -> p.getId() == id);
    }

    /**
     * Filter products by type and value.
     * @param type filter type (category, name, price)
     * @param value filter value
     * @return filtered list of products
     */
    public List<Product> filter(String type, String value) {
        switch (type.toLowerCase()) {
            case "category":
                return productList.stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase(value))
                        .collect(Collectors.toList());

            case "name":
                return productList.stream()
                        .filter(p -> p.getName().toLowerCase().contains(value.toLowerCase()))
                        .collect(Collectors.toList());

            case "price":
                double maxPrice = Double.parseDouble(value);
                return productList.stream()
                        .filter(p -> p.getPrice() <= maxPrice)
                        .collect(Collectors.toList());

            default:
                return new ArrayList<>();
        }
    }
}