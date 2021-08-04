package me.tdd.service;

import me.tdd.entity.Order;
import me.tdd.entity.OrderStateEnum;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order makeOrder(Order order) throws Exception;
    void changeOrderState(Order order, OrderStateEnum state);
}
