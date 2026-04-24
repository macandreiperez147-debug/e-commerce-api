package com.ws101.perez.ecommerceapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ws101.perez.ecommerceapi.model.Product;

@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private int nextId = 1;

    public ProductService() {

        productList.add(new Product(nextId++, "Wireless Mouse", "Ergonomic mouse", 799, "Electronics", 25, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Keyboard", "Mechanical keyboard", 2499, "Electronics", 15, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Headset", "Gaming headset", 1599, "Electronics", 20, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "T-Shirt", "Cotton shirt", 299, "Clothing", 50, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Shoes", "Running shoes", 1999, "Footwear", 18, "https://via.placeholder.com/150"));
    }

    // GET ALL
    public List<Product> getAll() {
        return productList;
    }

    // GET BY ID
    public Product getById(int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // CREATE
    public Product create(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    // UPDATE (PUT)
    public Product update(int id, Product newProduct) {
        Product existing = getById(id);
        if (existing == null) return null;

        newProduct.setId(id);

        productList.remove(existing);
        productList.add(newProduct);

        return newProduct;
    }

    // PATCH (partial update)
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

    // DELETE
    public boolean delete(int id) {
        return productList.removeIf(p -> p.getId() == id);
    }

    // FILTER
    public List<Product> filter(String filterType, String filterValue) {

        switch (filterType.toLowerCase()) {

            case "category":
                return productList.stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase(filterValue))
                        .collect(Collectors.toList());

            case "name":
                return productList.stream()
                        .filter(p -> p.getName().toLowerCase().contains(filterValue.toLowerCase()))
                        .collect(Collectors.toList());

            case "price":
                double price = Double.parseDouble(filterValue);
                return productList.stream()
                        .filter(p -> p.getPrice() <= price)
                        .collect(Collectors.toList());

            default:
                return new ArrayList<>();
        }
    }
}