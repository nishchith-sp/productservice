package com.nishchith.productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    private Category category;
    private int quantity;
}
