package com.bgpark.atdd.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductRepository productRepository;

    @DisplayName("create, 주문을 생성한다")
    @Test
    void create_주문_생성() {

        // given
        OrderService orderService = new OrderService(orderRepository, productRepository);

        // request 생성
        Product productRequest = new Product();
        productRequest.setId(1L);

        OrderItem orderItemRequest = new OrderItem();
        orderItemRequest.setProduct(productRequest);
        orderItemRequest.setQuantity(10);

        Orders request = new Orders();
        request.setOrderItems(Arrays.asList(orderItemRequest));
        request.setPrice(10000);

        // product 저장
        Product product = new Product();
        product.setId(1L);
        product.setName("햄버거");
        product.setPrice(1000);

        given(productRepository.findById(any())).willReturn(Optional.of(product));

        // order 저장
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("햄버거");
        product1.setPrice(1000);

        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(10);
        orderItem.setProduct(product1);

        Orders orders = new Orders();
        orders.setPrice(10000);
        orders.setId(1L);
        orders.setOrderItems(Arrays.asList(orderItem));

        given(orderRepository.save(any())).willReturn(orders);

        // when
        Orders savedOrder = orderService.create(request);

        // then
        assertAll(
                () -> assertThat(savedOrder.getPrice()).isEqualTo(10000),
                () -> assertThat(savedOrder.getOrderItems().get(0).getQuantity()).isEqualTo(10),
                () -> assertThat(savedOrder.getOrderItems().get(0).getProduct().getPrice()).isEqualTo(1000),
                () -> assertThat(savedOrder.getOrderItems().get(0).getProduct().getName()).isEqualTo("햄버거")
        );
    }

    @DisplayName("create, 주문 생성 시 주문 가격과 상품 가격 * 상품 수량이 다른 경우 에러를 발생한다")
    @Test
    void create_다른_가격() {

        // given
        OrderService orderService = new OrderService(orderRepository, productRepository);

        // request 생성
        Product productRequest = new Product();
        productRequest.setId(1L);

        OrderItem orderItemRequest = new OrderItem();
        orderItemRequest.setProduct(productRequest);
        orderItemRequest.setQuantity(-1);

        Orders request = new Orders();
        request.setOrderItems(Arrays.asList(orderItemRequest));
        request.setPrice(10000);

        // product 저장
        Product product = new Product();
        product.setId(1L);
        product.setName("햄버거");
        product.setPrice(1000);

        given(productRepository.findById(any())).willReturn(Optional.of(product));

        // when
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> orderService.create(request));

    }
}
