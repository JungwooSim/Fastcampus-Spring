package me.tdd.service;

import me.tdd.entity.Order;
import me.tdd.entity.Picker;
import me.tdd.entity.PickerStateEnum;
import me.tdd.entity.PickingList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PickerServiceTest {
    @Autowired
    PickerService pickerService;

    @Test
    void testAssignPickingList() {
        Picker picker = new Picker();
        picker.setPickerId(1L);
        picker.setState(PickerStateEnum.REST);

        PickingList pickingList = new PickingList();
        pickingList.setOrder(new Order());
        picker = pickerService.assignPickingList(picker, pickingList);

        Assertions.assertEquals(PickerStateEnum.ASSIGNED, picker.getState());
        Assertions.assertNotNull(picker.getAssignedOrder());
        Assertions.assertNotNull(picker.getAssignedPickingList());
        Assertions.assertNotNull(picker.getAssignedPickingList().getPicker());

    }
}
