package me.tdd.service;

import me.tdd.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PickingListServiceImpl implements PickingListService {
    @Autowired
    OrderService orderService;
    @Autowired
    PickerService pickerService;

    @Override
    public PickingList makePickingList(Order order) {
        PickingList pickingList = new PickingList();
        pickingList.setOrder(order);
        pickingList.setState(PickingStateEnum.NOTASSIGNED);

        Map<Sku, Integer> skuAmountMap = new HashMap<>();
        for (OrderDetail orderDetail : order.getOrderDetailList()) {
            skuAmountMap.put(orderDetail.getSku(), orderDetail.getAmount());
        }
        pickingList.setSkuAmountMap(skuAmountMap);

        //Order Service Change
        orderService.changeOrderState(order, OrderStateEnum.LISTMADED);

        return pickingList;
    }

    @Override
    public PickingList assignPicker(PickingList pickingList, Picker picker) {
        pickingList.setPicker(picker);
        pickingList.setState(PickingStateEnum.ASSIGNED);

        orderService.changeOrderState(pickingList.getOrder(), OrderStateEnum.ASSIGNED);

        if (picker.getAssignedPickingList() == null || !picker.getAssignedPickingList().equals(pickingList)) {
            pickerService.assignPickingList(picker, pickingList);
        }

        return pickingList;
    }
}
