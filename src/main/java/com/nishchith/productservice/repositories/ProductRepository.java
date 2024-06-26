package com.nishchith.productservice.repositories;

import com.nishchith.productservice.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @NonNull
    Optional<Product> findById(@NonNull Long id);

    @Override
    @NonNull
    List<Product> findAll();

    @Override
    @NonNull
    Product save(@NonNull Product product);

    @Override
    void delete(@NonNull Product product);


    List<Product> findByNameContaining(@NonNull String title, Pageable pageable);
}
