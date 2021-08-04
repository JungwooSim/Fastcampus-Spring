package me.tdd.service;

import me.tdd.entity.*;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PickingListServiceTest {
    @InjectMocks
    PickingListService pickingListService = new PickingListServiceImpl();

    @Mock
    OrderService orderService;

    @Spy
    PickerService pickerService = new PickerServiceImpl();

    Order order;

    @BeforeEach
    void orderSetup() {
        order = new Order();
        order.setOrderId(1L);
        order.setState(OrderStateEnum.ORDERED);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(1L);
        orderDetail.setAmount(10);
        orderDetail.setOrderDetailId(1L);
        orderDetail.setSku(new Sku());

        order.setOrderDetailList(Arrays.asList(orderDetail));
    }

    @Test
    void testMakePickingList() {
        PickingList assertPickingList = new PickingList();
        assertPickingList.setOrder(order);
        assertPickingList.setSkuAmountMap(
                Maps.newHashMap(
                        order.getOrderDetailList().get(0).getSku(),
                        order.getOrderDetailList().get(0).getAmount()));
        assertPickingList.setState(PickingStateEnum.NOTASSIGNED);
        assertPickingList.setPicker(null);

        PickingList pickingList = pickingListService.makePickingList(order);


        Assertions.assertEquals(assertPickingList.getOrder(), pickingList.getOrder());
        Assertions.assertEquals(assertPickingList.getState(), pickingList.getState());
        Assertions.assertEquals(assertPickingList.getSkuAmountMap().get(order.getOrderDetailList().get(0).getSku()),
                pickingList.getSkuAmountMap().get(order.getOrderDetailList().get(0).getSku()));

    }

    @Test
    void testAssignPicker() {
        PickingList pickingList = this.pickingListService.makePickingList(order);

        Picker picker = new Picker();

        PickingList assignedPickingList = pickingListService.assignPicker(pickingList, picker);

        Assertions.assertEquals(picker, assignedPickingList.getPicker());
        Assertions.assertEquals(PickingStateEnum.ASSIGNED, assignedPickingList.getState());
        Assertions.assertNotNull(assignedPickingList.getPicker().getAssignedPickingList());
    }
}
