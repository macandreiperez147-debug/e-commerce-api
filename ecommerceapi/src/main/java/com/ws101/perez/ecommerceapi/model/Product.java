package com.ws101.perez.ecommerceapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

/**
 * Represents a product in the ecommerce system.
 * Each product contains details such as name, description, price, stock,
 * and is associated with a category.
 * 
 * Relationship:
 * Many Products → One Category
 */
@Entity
@Table(name = "product")
public class Product {

    /**
     * Unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Description of the product.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Price of the product.
     */
    @Column(nullable = false)
    private double price;

    /**
     * Available stock quantity (cannot be negative).
     */
    @Min(0)
    @Column(nullable = false)
    private int stock;

    /**
     * Image filename or path of the product.
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * Category this product belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product() {}

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}