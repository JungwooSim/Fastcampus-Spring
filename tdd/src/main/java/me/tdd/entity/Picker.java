package me.tdd.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Picker {
    private Long pickerId;
    private PickerStateEnum state;
    private Order assignedOrder;
    private PickingList assignedPickingList;
}
