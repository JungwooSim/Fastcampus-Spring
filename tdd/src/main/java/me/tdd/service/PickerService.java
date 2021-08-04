package me.tdd.service;

import me.tdd.entity.Picker;
import me.tdd.entity.PickingList;
import org.springframework.stereotype.Service;

@Service
public interface PickerService {
    Picker assignPickingList(Picker picker, PickingList pickingList);
}
