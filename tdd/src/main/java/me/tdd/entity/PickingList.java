package me.tdd.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PickingList {
    private Long id;
    private Order order;
    private Map<Sku,Integer> skuAmountMap;
    private Map<Sku,Integer> pickedMap;
    private PickingStateEnum state;
    private Picker picker;
}
