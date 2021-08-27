package com.bgpark.atdd.domain.order;

import java.util.Arrays;

public class OrderFixture {

    public static Orders createOrder(int 주문_가격, OrderItem orderItem) {
        Orders orders = new Orders();
        orders.setPrice(주문_가격);
        orders.setId(1L);
        orders.setOrderItems(Arrays.asList(orderItem));
        return orders;
    }

    public static OrderItem createOrderItem(int 상품_수량, Product product1) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(상품_수량);
        orderItem.setProduct(product1);
        return orderItem;
    }

    public static Product createProduct(String 상품_이름, int 상품_가격) {
        Product product = new Product();
        product.setId(1L);
        product.setName(상품_이름);
        product.setPrice(상품_가격);
        return product;
    }

    public static Orders createSaveOrderRequest(int 상품_수량, int 주문_가격) {
        Product productRequest = new Product();
        productRequest.setId(1L);

        OrderItem orderItemRequest = new OrderItem();
        orderItemRequest.setProduct(productRequest);
        orderItemRequest.setQuantity(상품_수량);

        Orders request = new Orders();
        request.setOrderItems(Arrays.asList(orderItemRequest));
        request.setPrice(주문_가격);
        return request;
    }
}
