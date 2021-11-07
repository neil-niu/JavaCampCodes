package com.neil.db.service.impl;

import com.neil.db.dao.OrderDAO;
import com.neil.db.model.Order;
import com.neil.db.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderMapper;


    @Override
    public int batchAddOrder(List<Order> orderList) {
        return orderMapper.batchAddOrder(orderList);
    }
}

