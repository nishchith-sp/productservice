package com.nishchith.productservice.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String s) {
        super(s);
    }
}