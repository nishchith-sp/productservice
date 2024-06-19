package com.nishchith.productservice.controllers;

import com.nishchith.productservice.exceptions.ProductNotFoundException;
import com.nishchith.productservice.models.Product;
import com.nishchith.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

//    @Autowired
//    ProductController productController;
//
//    @MockBean
//    @Qualifier("selfProductServiceDB")
//    ProductService productService;
//
//    @BeforeEach
//    public void setUp() throws ProductNotFoundException {
//        //Arrange
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("test");
//        product.setDescription("description");
//
//        //Rule
//        when(productService.getProductById(any(Long.class))).thenReturn(product);
//        when(productService.getAllProducts()).thenReturn(Collections.singletonList(product));
//
//    }
//
//    @Test
//    void getProductById() throws ProductNotFoundException {
//
//        //Act
//        ResponseEntity<Product> productResponseEntity = productController.getProductById(1L);
//
//        //Assert
//        assertNotNull(productResponseEntity);
//        assertEquals(HttpStatus.OK, productResponseEntity.getStatusCode());
//        assertNotNull(productResponseEntity.getBody());
//        assertEquals(1L, productResponseEntity.getBody().getId());
//        assertEquals("test", productResponseEntity.getBody().getName());
//    }
//
//    @Test
//    void getAllProducts() throws ProductNotFoundException {
//        //Act
//        ResponseEntity<List<Product>> productResponseEntity = productController.getAllProducts();
//
//        //Assert
//        assertNotNull(productResponseEntity);
//        assertEquals(HttpStatus.OK, productResponseEntity.getStatusCode());
//        assertNotNull(productResponseEntity.getBody());
//        assertEquals(1, productResponseEntity.getBody().size());
//    }

}