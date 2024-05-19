package com.nishchith.productservice.services;

import com.nishchith.productservice.exceptions.ProductNotCreatedException;
import com.nishchith.productservice.exceptions.ProductNotFoundException;
import com.nishchith.productservice.models.Category;
import com.nishchith.productservice.models.Product;
import com.nishchith.productservice.repositories.CategoryRepository;
import com.nishchith.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductServiceDB")
public class SelfProductServiceDB implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public SelfProductServiceDB(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException("");
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {

        return this.productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotCreatedException {
        return this.productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotCreatedException {
        return this.productRepository.save(product);
    }

    @Override
    public Product createProduct(Product product) throws ProductNotCreatedException {

        Optional<Category> category =  categoryRepository.getCategoryByName(product.getCategory().getName());

        if(category.isEmpty()){
            Category newCategory = product.getCategory();
            product.setCategory(categoryRepository.save(newCategory));
        } else{
            product.setCategory(category.get());
        }

        return this.productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Product product) {
        this.productRepository.delete(product);
        return product;
    }

}
