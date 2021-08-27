package com.bgpark.atdd.domain.order;

import java.util.HashMap;
import java.util.Map;

class InMemoryOrderRepository implements OrderRepository {

    Map<Long, Orders> db = new HashMap<>();
    Long id = 0L;

    @Override
    public Orders save(Orders orders) {
        db.put(++id, orders);
        return new Orders(id, orders.getPrice(), orders.getOrderItems());
    }
}
