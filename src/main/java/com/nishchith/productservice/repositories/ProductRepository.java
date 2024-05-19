package com.nishchith.productservice.repositories;

import com.nishchith.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @NonNull
    Optional<Product> findById(@NonNull Long id);

}
