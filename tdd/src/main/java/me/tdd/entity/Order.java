package me.tdd.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private Long orderId;
    private OrderStateEnum state;
    public List<OrderDetail> orderDetailList;
}
