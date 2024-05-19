package com.nishchith.productservice.services;

import com.nishchith.productservice.exceptions.ProductNotCreatedException;
import com.nishchith.productservice.exceptions.ProductNotFoundException;
import com.nishchith.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts() throws ProductNotFoundException;
    Product updateProduct(Long id, Product product) throws ProductNotCreatedException;
    Product replaceProduct(Long id, Product product) throws ProductNotCreatedException;
    Product createProduct(Product product) throws ProductNotCreatedException;
    Product deleteProduct(Product product);


}
