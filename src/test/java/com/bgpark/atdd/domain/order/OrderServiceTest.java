package com.bgpark.atdd.domain.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static com.bgpark.atdd.domain.order.OrderFixture.*;
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

    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, productRepository);
    }

    @DisplayName("create, 주문을 생성한다")
    @Test
    void create_주문_생성() {

        // given
        String 상품_이름 = "햄버거";
        int 상품_수량 = 10;
        int 상품_가격 = 1000;
        int 주문_가격 = 10000;

        Orders request = createSaveOrderRequest(상품_수량, 주문_가격);

        Product product = createProduct(상품_이름, 상품_가격);
        given(productRepository.findById(any())).willReturn(Optional.of(product));

        Product product1 = createProduct(상품_이름, 상품_가격);
        OrderItem orderItem = createOrderItem(상품_수량, product1);
        Orders orders = createOrder(주문_가격, orderItem);
        given(orderRepository.save(any())).willReturn(orders);

        // when
        Orders savedOrder = orderService.create(request);

        // then
        assertAll(
                () -> assertThat(savedOrder.getPrice()).isEqualTo(주문_가격),
                () -> assertThat(savedOrder.getOrderItems().get(0).getQuantity()).isEqualTo(상품_수량),
                () -> assertThat(savedOrder.getOrderItems().get(0).getProduct().getPrice()).isEqualTo(상품_가격),
                () -> assertThat(savedOrder.getOrderItems().get(0).getProduct().getName()).isEqualTo(상품_이름)
        );
    }

    @DisplayName("create, 주문 생성 시 주문 가격과 상품 가격 * 상품 수량이 다른 경우 에러를 발생한다")
    @Test
    void create_다른_가격() {

        // given
        // request 생성
        Orders request = createSaveOrderRequest(-1, 10000);

        // product 저장
        Product product = createProduct("햄버거", 1000);

        given(productRepository.findById(any())).willReturn(Optional.of(product));

        // when
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> orderService.create(request));

    }
}
