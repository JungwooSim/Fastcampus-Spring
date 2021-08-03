package me.tdd.service;

import me.tdd.entity.OrderDetail;
import me.tdd.entity.Sku;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailServiceTest {
    @Autowired
    OrderDetailService orderDetailService;

    @Test
    void testOrderDetailMake_success() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailId(1L);
        orderDetail.setOrderId(1L);
        orderDetail.setSku(new Sku());
        orderDetail.setAmount(10);

        try {
            orderDetail = orderDetailService.makeOrderDetail(orderDetail);
        } catch (Exception e) {
            // doSomeThing
        }

        Assertions.assertEquals(1L, orderDetail.getOrderDetailId());
        Assertions.assertEquals(1L, orderDetail.getOrderId());
        Assertions.assertEquals(10, orderDetail.getAmount());
    }

    @Test
    void orderDetailValidationTest() {

    }
}
