package com.bgpark.atdd.domain.order;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    @OneToMany
    private List<OrderItem> orderItems;

    public Orders() {
    }

    public Orders(int price, List<OrderItem> orderItems) {
        this.price = price;
        this.orderItems = orderItems;
    }

    public Orders(Long id, int price, List<OrderItem> orderItems) {
        this.id = id;
        this.price = price;
        this.orderItems = orderItems;
    }

    public void checkPrice() {
        if (this.price != getTotalOrderItemsPrice()) {
            throw new IllegalStateException();
        }
    }

    private int getTotalOrderItemsPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
