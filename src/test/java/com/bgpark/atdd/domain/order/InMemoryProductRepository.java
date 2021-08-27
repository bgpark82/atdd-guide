package com.bgpark.atdd.domain.order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository{

    Map<Long, Product> db = new HashMap<>();
    Long id = 0L;

    @Override
    public Product save(Product product) {
        db.put(++id, product);
        return new Product(id, product.getName(), product.getPrice());
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = db.get(id);
        return Optional.of(product);
    }
}
