package com.nishchith.productservice.services;

import com.nishchith.productservice.dtos.FakeStoreProductDTO;
import com.nishchith.productservice.exceptions.ProductNotCreatedException;
import com.nishchith.productservice.exceptions.ProductNotFoundException;
import com.nishchith.productservice.models.Category;
import com.nishchith.productservice.models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    private final RedisTemplate<String,Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCTS_"+id);

        if(product != null) {
            return product;
        }

        FakeStoreProductDTO fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDTO.class);

        if (fakeStoreProductDto == null)
            throw new ProductNotFoundException("Sorry, product not available");

        product =convertFakeStoreDTOToProduct(fakeStoreProductDto);
        redisTemplate.opsForHash().put("PRODUCTS","PRODUCTS_"+id,product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        FakeStoreProductDTO[] fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDTO[].class);

        if (fakeStoreProductDto == null)
            throw new ProductNotFoundException("Sorry, products not available");

        List<Product> productList = new ArrayList<>();

        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDto) {
            Product product = convertFakeStoreDTOToProduct(fakeStoreProductDTO);
            productList.add(product);
        }

        return productList;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotCreatedException {
        FakeStoreProductDTO fakeStoreProductDTO = convertProductToFakeStoreDTOForUpdate(product);
        FakeStoreProductDTO createdFakeStoreProductDTO = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, fakeStoreProductDTO,
                FakeStoreProductDTO.class);

        if (createdFakeStoreProductDTO == null) {
            throw new ProductNotCreatedException("");
        }

        return convertFakeStoreDTOToProduct(createdFakeStoreProductDTO);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotCreatedException {
        FakeStoreProductDTO fakeStoreProductDTO = convertProductToFakeStoreDTO(product);
        FakeStoreProductDTO createdFakeStoreProductDTO = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, fakeStoreProductDTO,
                FakeStoreProductDTO.class);

        if (createdFakeStoreProductDTO == null) {
            throw new ProductNotCreatedException("");
        }

        return convertFakeStoreDTOToProduct(createdFakeStoreProductDTO);
    }

    @Override
    public Product createProduct(Product product) throws ProductNotCreatedException {

        FakeStoreProductDTO fakeStoreProductDTO = convertProductToFakeStoreDTO(product);
        FakeStoreProductDTO createdFakeStoreProductDTO = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDTO,
                FakeStoreProductDTO.class);

        if (createdFakeStoreProductDTO == null) {
            throw new ProductNotCreatedException("");
        }

        return convertFakeStoreDTOToProduct(createdFakeStoreProductDTO);
    }

    @Override
    public Product deleteProduct(Product product) {
        return product;
    }

    private FakeStoreProductDTO convertProductToFakeStoreDTOForUpdate(Product product) {
        FakeStoreProductDTO dto = new FakeStoreProductDTO();
        if (product.getId() != null) {
            dto.setId(product.getId());
        }
        if (product.getName() != null) {
            dto.setTitle(product.getName());
        }
        if (product.getDescription() != null) {
            dto.setDescription(product.getDescription());
        }
        if (product.getPrice() != 0) {
            dto.setPrice(product.getPrice());
        }
        if (product.getCategory() != null) {
            Category category = new Category();
            if (category.getId() != null) {
                category.setId(category.getId());
            }
            if (category.getName() != null) {
                category.setName(category.getName());
            }
            dto.setCategory(category.getName());
        }


        return dto;
    }

    private FakeStoreProductDTO convertProductToFakeStoreDTO(Product product) {
        FakeStoreProductDTO dto = new FakeStoreProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());

        Category category = new Category();
        category.setId(category.getId());
        category.setName(category.getName());
        dto.setCategory(category.getName());

        return dto;
    }

    private Product convertFakeStoreDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());

        Category category = new Category();
        category.setId(fakeStoreProductDTO.getId());
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);

        return product;
    }
}
