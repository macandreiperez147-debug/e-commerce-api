package com.ws101.perez.ecommerceapi.exception;

/**
 * Custom exception for product not found.
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}