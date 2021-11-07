package com.neil.db.service;

import com.neil.db.model.Order;

import java.util.List;

public interface OrderService {
    int batchAddOrder(List<Order> orderList);
}
