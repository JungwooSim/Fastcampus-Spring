package me.tdd.service;

import me.tdd.entity.PickingList;
import me.tdd.entity.Sku;
import org.springframework.stereotype.Service;

@Service
public interface PickingService {
    void pick(PickingList pickingList, Sku sku) throws Exception;
}
