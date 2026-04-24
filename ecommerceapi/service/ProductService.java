package com.ws101.perez.ecommerceapi.service;

import com.ws101.perez.ecommerceapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();
    private int nextId = 1;

    public ProductService() {
        // 10 sample products
        for (int i = 1; i <= 10; i++) {
            productList.add(new Product(
                nextId++,
                "Product " + i,
                "Sample description",
                100 * i,
                "Electronics",
                10,
                ""
            ));
        }
    }

    public List<Product> getAll() {
        return productList;
    }

    public Product getById(int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product create(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    public Product update(int id, Product newProduct) {
        Product existing = getById(id);
        if (existing == null) return null;

        newProduct.setId(id);
        productList.remove(existing);
        productList.add(newProduct);
        return newProduct;
    }

    public Product patch(int id, Product update) {
        Product existing = getById(id);
        if (existing == null) return null;

        if (update.getName() != null) existing.setName(update.getName());
        if (update.getPrice() != 0) existing.setPrice(update.getPrice());

        return existing;
    }

    public boolean delete(int id) {
        return productList.removeIf(p -> p.getId() == id);
    }

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
                return new ArrayList<>();
        }
    }
}