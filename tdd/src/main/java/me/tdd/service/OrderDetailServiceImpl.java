package me.tdd.service;

import me.tdd.entity.OrderDetail;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public OrderDetail makeOrderDetail(OrderDetail orderDetail) throws Exception {
        return orderDetail;
    }
}
