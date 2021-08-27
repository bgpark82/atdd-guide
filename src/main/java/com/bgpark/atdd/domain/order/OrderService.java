package com.bgpark.atdd.domain.order;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    Orders create(Orders request) {
        List<OrderItem> orderItems = createOrderItems(request);
        Orders orders = new Orders(request.getPrice(), orderItems);

        orders.checkPrice();

        return orderRepository.save(orders);
    }

    private List<OrderItem> createOrderItems(Orders request) {
        return request.getOrderItems()
                .stream()
                .map(orderItem -> new OrderItem(
                        productRepository.findById(orderItem.getProduct().getId()).get(),
                        orderItem.getQuantity()))
                .collect(Collectors.toList());
    }
}
