package me.tdd.service;

import me.tdd.entity.PickerStateEnum;
import me.tdd.entity.PickingList;
import me.tdd.entity.PickingStateEnum;
import me.tdd.entity.Sku;
import org.springframework.stereotype.Service;

@Service
public class PickingServiceImpl implements PickingService {
    @Override
    public void pick(PickingList pickingList, Sku sku) throws Exception {
        if(!pickingList.getSkuAmountMap().keySet().contains(sku)){
            throw new Exception("wrong sku");
        } else {
            if(pickingList.getPickedMap().get(sku) == pickingList.getSkuAmountMap().get(sku)){
                throw new Exception("to much sku");
            }
            pickingList.getPickedMap().put(sku, pickingList.getPickedMap().get(sku) + 1);
        }

        this.setStatus(pickingList, null);
    }

    private void setStatus(PickingList pickingList, PickingStateEnum status) {
        if (status == null) {
            boolean isDone = true;
            for (Sku sku: pickingList.getSkuAmountMap().keySet()) {
                isDone = pickingList.getSkuAmountMap().get(sku) == pickingList.getPickedMap().get(sku);
            }

            if (isDone) {
                setStatus(pickingList, PickingStateEnum.DONE);
            } else {
                pickingList.setState(PickingStateEnum.PROGRESS);
                pickingList.getPicker().setState(PickerStateEnum.PROCESS);
            }

        } else {
            pickingList.setState(status);
            if (status == PickingStateEnum.DONE) {
                pickingList.getPicker().setState(PickerStateEnum.DONE);

            }
            if (status == PickingStateEnum.ERROR) {
                pickingList.getPicker().setState(PickerStateEnum.ERROR);
            }
            if (status == PickingStateEnum.PENDING) {
                pickingList.getPicker().setState(PickerStateEnum.ERROR);
            }
        }
    }
}
