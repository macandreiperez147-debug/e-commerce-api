package com.ws101.perez.ecommerceapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ws101.perez.ecommerceapi.model.Product;

/**
 * Service class for product-related operations.
 *
 * Provides business logic for creating, reading, updating, deleting,
 * and filtering products. This class uses in-memory storage (ArrayList)
 * instead of a database for simplicity and learning purposes.
 *
 * Constraints:
 * - Price must be greater than 0
 * - Stock must be non-negative
 * - ID is auto-generated
 *
 * @author Mac Andrei Perez
 * Collaborator: Angelica Naza
 * @since 2026
 * @see Product
 */
@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private int nextId = 1;

    /**
     * Initializes sample product data.
     */
    public ProductService() {
        productList.add(new Product(nextId++, "Wireless Mouse", "Ergonomic mouse", 799, "Electronics", 25, "image.jpg"));
        productList.add(new Product(nextId++, "Keyboard", "Mechanical keyboard", 1999, "Electronics", 10, "image.jpg"));
        productList.add(new Product(nextId++, "Headset", "Gaming headset", 1599, "Electronics", 15, "image.jpg"));
    }

    /**
     * Retrieves all products.
     *
     * @return list of all products
     */
    public List<Product> getAll() {
        return productList;
    }

    /**
     * Retrieves a product by ID.
     *
     * @param id product ID to search
     * @return Product if found, otherwise null
     */
    public Product getById(int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Creates a new product and assigns an auto-generated ID.
     *
     * @param product product data to create
     * @return created product with ID
     */
    public Product create(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    /**
     * Replaces an existing product entirely.
     *
     * @param id product ID to update
     * @param newProduct new product data
     * @return updated product, or null if not found
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
     * Partially updates a product (only provided fields).
     *
     * @param id product ID to update
     * @param update product fields to update
     * @return updated product, or null if not found
     */
    public Product patch(int id, Product update) {
        Product existing = getById(id);
        if (existing == null) return null;

        if (update.getName() != null) existing.setName(update.getName());
        if (update.getDescription() != null) existing.setDescription(update.getDescription());
        if (update.getCategory() != null) existing.setCategory(update.getCategory());
        if (update.getImageUrl() != null) existing.setImageUrl(update.getImageUrl());
        if (update.getPrice() != 0) existing.setPrice(update.getPrice());
        if (update.getStock() != 0) existing.setStock(update.getStock());

        return existing;
    }

    /**
     * Deletes a product by ID.
     *
     * @param id product ID to delete
     * @return true if deleted, false if not found
     */
    public boolean delete(int id) {
        return productList.removeIf(p -> p.getId() == id);
    }

    /**
     * Filters products by category or name.
     *
     * @param type filter type (category or name)
     * @param value filter value to match
     * @return list of filtered products
     * @throws IllegalArgumentException if filter type is invalid
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

            default:
                throw new IllegalArgumentException("Invalid filter type: " + type);
        }
    }
}