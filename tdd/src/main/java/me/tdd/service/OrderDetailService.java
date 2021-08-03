package me.tdd.service;

import me.tdd.entity.OrderDetail;
import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService {

    OrderDetail makeOrderDetail(OrderDetail orderDetail) throws Exception;
}
