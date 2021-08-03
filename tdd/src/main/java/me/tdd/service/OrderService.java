package me.tdd.service;

import me.tdd.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Order makeOrder(Order order);
}
