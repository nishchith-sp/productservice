package com.nishchith.productservice.controllers;

import com.nishchith.productservice.dtos.SearchRequestDTO;
import com.nishchith.productservice.exceptions.ProductNotFoundException;
import com.nishchith.productservice.models.Product;
import com.nishchith.productservice.services.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class SearchController {

    private SearchService searchService;

    SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestBody SearchRequestDTO searchRequestDTO) throws ProductNotFoundException {

        List<Product> products = searchService.search(searchRequestDTO.getKeyword(), searchRequestDTO.getPageNumber(), searchRequestDTO.getPageSize());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
