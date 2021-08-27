package com.bgpark.atdd.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository {

    Orders save(Orders orders);
}

interface JpaOrderRepository extends OrderRepository, JpaRepository<Orders, Long> {}

