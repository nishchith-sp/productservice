package com.nishchith.productservice.repositories;

import com.nishchith.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    @NonNull
    Optional<Category> findById(@NonNull Long id);


    Optional<Category> getCategoryByName(@NonNull String name);

    @Override
    @NonNull
    List<Category> findAll();

    @Override
    @NonNull
    Category save(@NonNull Category product);

    @Override
    void delete(@NonNull Category product);
}
