package com.nishchith.productservice.services;

import com.nishchith.productservice.exceptions.ProductNotCreatedException;
import com.nishchith.productservice.exceptions.ProductNotFoundException;
import com.nishchith.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceDB")
public class SelfProductServiceDB implements ProductService{

    private final FakeStoreProductService fakeStoreProductService;

    public SelfProductServiceDB(FakeStoreProductService fakeStoreProductService) {
        this.fakeStoreProductService = fakeStoreProductService;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return fakeStoreProductService.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        return fakeStoreProductService.getAllProducts();
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotCreatedException {
        return fakeStoreProductService.updateProduct(id,product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotCreatedException {
        return fakeStoreProductService.replaceProduct(id,product);
    }

    @Override
    public Product createProduct(Product product) throws ProductNotCreatedException {
        return fakeStoreProductService.createProduct(product);
    }

    @Override
    public Product deleteProduct() {
        return null;
    }


}
