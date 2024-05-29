package com.nishchith.productservice.controllers;

import com.nishchith.productservice.exceptions.ProductNotCreatedException;
import com.nishchith.productservice.exceptions.ProductNotFoundException;
import com.nishchith.productservice.models.Product;
import com.nishchith.productservice.services.ProductService;
import com.nishchith.productservice.services.TokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final TokenService tokenService;
    ProductService productService;

    ProductController(@Qualifier("selfProductServiceRemote") ProductService productService, TokenService tokenService) {
        this.productService = productService;
        this.tokenService = tokenService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@RequestHeader("token") String token, @PathVariable Long id) throws ProductNotFoundException {

        if(!tokenService.validateToken(token)){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        Product product = productService.getProductById(id);

        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() throws ProductNotFoundException {

        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ProductNotCreatedException {
        Product createdProduct = productService.createProduct(product);

        return new ResponseEntity<>(createdProduct,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotCreatedException {
        Product createdProduct = productService.updateProduct(id, product);

        return new ResponseEntity<>(createdProduct,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotCreatedException {
        Product createdProduct = productService.replaceProduct(id, product);

        return new ResponseEntity<>(createdProduct,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Product> removeProduct(@PathVariable Long id) {
        return null;
    }

        @ExceptionHandler({RuntimeException.class, NullPointerException.class})
    public ResponseEntity<String> handleException() {
        System.out.println("Something went Wrong");
        return new ResponseEntity<>("Something went Wrong", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException() {
        System.out.println("Something went Wrong");
        return new ResponseEntity<>("Sorry, Product not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotCreatedException.class)
    public ResponseEntity<String> handleProductNotCreatedException() {
        System.out.println("Something went Wrong");
        return new ResponseEntity<>("Sorry, Unable to created the Product at the moment", HttpStatus.NOT_FOUND);
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleIndexException() {
//        System.out.println("Something Wrong");
//        return new ResponseEntity<>("Something Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
