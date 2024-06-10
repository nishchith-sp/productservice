package com.nishchith.productservice.services;

import com.nishchith.productservice.models.Product;
import com.nishchith.productservice.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;

    SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> search(String keyword, int pageNumber, int pageSize) {
        return productRepository.findByNameContaining(keyword, PageRequest.ofSize(pageSize).withPage(pageNumber));
    }
}
