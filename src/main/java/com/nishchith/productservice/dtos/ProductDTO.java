package com.nishchith.productservice.dtos;

import com.nishchith.productservice.models.Category;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    private String name;
    private String description;
    private double price;
    private Category category;
    private int quantity;
}
