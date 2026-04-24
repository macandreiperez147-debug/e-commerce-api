package com.ws101.perez.ecommerceapi.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stock;
    private String imageUrl;
}