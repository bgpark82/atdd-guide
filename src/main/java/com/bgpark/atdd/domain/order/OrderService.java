package com.bgpark.atdd.domain.order;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

        int menuPrice = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItem orderItemRequest : request.getOrderItems()) {
            // product 호출
            Long productId = orderItemRequest.getProduct().getId();
            Product product = productRepository.findById(productId).get();

            // orderItem 생성
            OrderItem orderItem = new OrderItem(
                    product,
                    orderItemRequest.getQuantity());

            orderItems.add(orderItem);

            // 가격 계산
            int productPrice = product.getPrice();
            int price = productPrice * orderItemRequest.getQuantity();
            menuPrice += price;
        }

        // 가격 검증
        if (request.getPrice() != menuPrice) {
            throw new IllegalStateException();
        }

        // order 생성
        Orders orders = new Orders(menuPrice, orderItems);

        return orderRepository.save(orders);
    }
}
