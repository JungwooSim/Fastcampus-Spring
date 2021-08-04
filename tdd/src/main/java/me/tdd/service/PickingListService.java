package me.tdd.service;

import me.tdd.entity.Order;
import me.tdd.entity.Picker;
import me.tdd.entity.PickingList;

public interface PickingListService {
    PickingList makePickingList(Order order);
    PickingList assignPicker(PickingList pickingList, Picker picker);
}
