package com.bgpark.atdd.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);
    Optional<Product> findById(Long id);
}

interface JpaProductRepository extends ProductRepository, JpaRepository<Product, Long> {

}



