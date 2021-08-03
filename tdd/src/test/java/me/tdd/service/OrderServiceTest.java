package me.tdd.service;

import me.tdd.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void testOrderMake_success() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setState("Ordered");

        order = orderService.makeOrder(order);

        Assertions.assertEquals(1L, order.getOrderId());
        Assertions.assertEquals("Ordered", order.getState());
    }
}
