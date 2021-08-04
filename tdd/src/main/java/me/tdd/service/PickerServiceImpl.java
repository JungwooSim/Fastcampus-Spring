package me.tdd.service;

import me.tdd.entity.Picker;
import me.tdd.entity.PickerStateEnum;
import me.tdd.entity.PickingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PickerServiceImpl implements PickerService {
    @Autowired
    PickingListService pickingListService;

    @Override
    public Picker assignPickingList(Picker picker, PickingList pickingList) {

        picker.setAssignedPickingList(pickingList);
        picker.setAssignedOrder(pickingList.getOrder());
        picker.setState(PickerStateEnum.ASSIGNED);

        if (pickingList.getPicker() == null || !picker.equals(pickingList.getPicker())) {
            pickingListService.assignPicker(pickingList, picker);
        }

        return picker;
    }
}
