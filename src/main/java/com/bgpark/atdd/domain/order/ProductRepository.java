package com.bgpark.atdd.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{

    Product save(Product product);
    Optional<Product> findById(Long id);
}

