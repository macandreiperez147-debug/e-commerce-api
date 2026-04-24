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

        productList.add(new Product(nextId++, "Wireless Mouse", "Ergonomic wireless mouse with RGB lighting", 799, "Electronics", 25, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Mechanical Keyboard", "RGB mechanical keyboard with blue switches", 2499, "Electronics", 15, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Gaming Headset", "Surround sound gaming headset with mic", 1599, "Electronics", 20, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Smartphone Stand", "Adjustable aluminum phone stand", 299, "Accessories", 40, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Power Bank", "10000mAh fast charging power bank", 899, "Electronics", 30, "https://via.placeholder.com/150"));

        productList.add(new Product(nextId++, "Cotton T-Shirt", "Comfortable plain cotton t-shirt", 299, "Clothing", 50, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Denim Jeans", "Slim fit denim jeans", 1299, "Clothing", 35, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Hoodie Jacket", "Warm hoodie jacket for casual wear", 1499, "Clothing", 20, "https://via.placeholder.com/150"));

        productList.add(new Product(nextId++, "Running Shoes", "Lightweight running shoes", 1999, "Footwear", 18, "https://via.placeholder.com/150"));
        productList.add(new Product(nextId++, "Backpack", "Durable travel backpack with multiple compartments", 1099, "Accessories", 22, "https://via.placeholder.com/150"));
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