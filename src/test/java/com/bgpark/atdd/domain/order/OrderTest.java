package com.bgpark.atdd.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class OrderTest {

    @DisplayName("checkPrice, 주문 가격이 상품 수량 * 상품 가격과 다를 경우 에러를 발생한다")
    @Test
    void checkPrice() {
        // given
        Product product = new Product(1L, "햄버거", 1000);
        OrderItem orderItem = new OrderItem(product, 1);
        Orders orders = new Orders(10000, Arrays.asList(orderItem));

        // when
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> orders.checkPrice());
    }
}
