package com.neil.db.dao;

import com.neil.db.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderDAO {
    int batchAddOrder(List<Order> orderList);
}
