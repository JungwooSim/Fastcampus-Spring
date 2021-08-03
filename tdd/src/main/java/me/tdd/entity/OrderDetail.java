package me.tdd.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {
    private Long orderDetailId;
    private Long orderId;
    private Sku sku;
    private int amount;
}
