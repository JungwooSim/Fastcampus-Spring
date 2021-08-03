package me.tdd.service;

import me.tdd.entity.Order;
import me.tdd.entity.OrderDetail;
import me.tdd.entity.OrderStateEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrderServiceTest {
    @InjectMocks
    OrderService orderService = new OrderServiceImpl();

    @Mock
    OrderDetailService orderDetailService;

//    @Autowired
//    OrderService orderService;

    Order orderSuccess;
    Order orderFail;

    @BeforeEach
    public void orderInit() {
        OrderDetail orderDetail = new OrderDetail();
        orderSuccess = new Order();
        orderSuccess.setOrderId(1L);
        orderSuccess.setState(OrderStateEnum.ORDERED);
        orderSuccess.setOrderDetailList(Collections.singletonList(orderDetail));

        orderFail = new Order();
        orderFail.setOrderId(null);
        orderFail.setState(null);
    }

    @Test
    void testOrderMake_success() {
        Order order = new Order();

        try {
            order = orderService.makeOrder(orderSuccess);
        } catch (Exception e) {
        }

        Assertions.assertEquals(1L, order.getOrderId());
        Assertions.assertEquals(OrderStateEnum.ORDERED, order.getState());
    }

    // TODO : testOrderMake_success 와 비슷해서 하나로 합쳐도 상관없지만, 추가로 어떻게 사용될지 모르므로 우선 보류
    @Test
    void testOrderValidation_success() {
        Order order = new Order();

        try {
            order = orderService.makeOrder(orderSuccess);
        } catch (Exception e) {
            fail("should not throw exception");
        }

        Assertions.assertEquals(1L, order.getOrderId());
    }

    @Test
    void testOrderMake_fail() {
        Exception e = Assertions.assertThrows(Exception.class, () -> {
            orderService.makeOrder(orderFail);
        });

        Assertions.assertEquals("order validation fail", e.getMessage());
    }
}
