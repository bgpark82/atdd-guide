package com.bgpark.atdd.domain.order;

import java.util.Arrays;

public class OrderFixture {

    public static Orders createOrder(int 주문_가격, OrderItem orderItem) {
        return new Orders(1L, 주문_가격, Arrays.asList(orderItem));
    }

    public static OrderItem createOrderItem(int 상품_수량, Product product1) {
        return new OrderItem(1L, product1, 상품_수량);
    }

    public static Product createProduct(String 상품_이름, int 상품_가격) {
        return new Product(1L, 상품_이름, 상품_가격);
    }

    public static Orders createSaveOrderRequest(int 상품_수량, int 주문_가격) {
        Product productRequest = new Product(1L, null, 0);
        OrderItem orderItemRequest = new OrderItem(productRequest, 상품_수량);
        Orders request = new Orders(주문_가격, Arrays.asList(orderItemRequest));
        return request;
    }
}
