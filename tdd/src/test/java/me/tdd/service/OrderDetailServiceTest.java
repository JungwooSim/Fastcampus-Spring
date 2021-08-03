package me.tdd.service;

import me.tdd.entity.OrderDetail;
import me.tdd.entity.Sku;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class OrderDetailServiceTest {
    @Autowired
    OrderDetailService orderDetailService;

    OrderDetail orderDetailSuccess;
    OrderDetail orderDetailFail;

    @BeforeEach
    void setUp() {
        orderDetailSuccess = new OrderDetail();
        orderDetailSuccess.setOrderDetailId(1L);
        orderDetailSuccess.setOrderId(1L);
        orderDetailSuccess.setSku(new Sku());
        orderDetailSuccess.setAmount(10);

        orderDetailFail = new OrderDetail();
        orderDetailFail.setOrderDetailId(2L);
    }

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
    void testOrderDetailValidation_success() {
        OrderDetail orderDetail = new OrderDetail();

        try {
            orderDetail = orderDetailService.makeOrderDetail(orderDetailSuccess);
        } catch (Exception e) {
            fail("should not throw exception");
        }

        Assertions.assertEquals(1L, orderDetail.getOrderDetailId());
    }

    @Test
    void testOrderDetailValidation_fail() {
        Exception e = Assertions.assertThrows(Exception.class, () -> {
            orderDetailService.makeOrderDetail(orderDetailFail);
        });

        Assertions.assertEquals("orderDetail validation fail", e.getMessage());
    }
}
