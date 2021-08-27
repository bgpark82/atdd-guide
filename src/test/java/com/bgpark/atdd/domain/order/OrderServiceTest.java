package com.bgpark.atdd.domain.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bgpark.atdd.domain.order.OrderFixture.createProduct;
import static com.bgpark.atdd.domain.order.OrderFixture.createSaveOrderRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OrderServiceTest {

    String 상품_이름 = "햄버거";
    int 상품_수량 = 10;
    int 상품_가격 = 1000;
    int 주문_가격 = 10000;

    OrderRepository orderRepository = new InMemoryOrderRepository();
    ProductRepository productRepository = new InMemoryProductRepository();

    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, productRepository);
        Product product = createProduct(상품_이름, 상품_가격);
        productRepository.save(product);
    }

    @DisplayName("create, 주문을 생성한다")
    @Test
    void create_주문_생성() {
        // given
        Orders request = createSaveOrderRequest(상품_수량, 주문_가격);

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
        Orders request = createSaveOrderRequest(-1, 10000);

        // when
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> orderService.create(request));

    }
}
