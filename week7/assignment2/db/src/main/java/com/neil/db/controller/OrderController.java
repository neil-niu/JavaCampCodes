package com.neil.db.controller;

import com.neil.db.dao.OrderDAO;
import com.neil.db.model.Order;
import com.neil.db.utils.CharacterUtil;
import com.neil.db.utils.DateUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author neil niu
 * @date 2021/11/6 15:21
 */


@RestController
public class OrderController {
    @Resource
    OrderDAO orderDAO;

    @PostMapping("/addOrder")
    public void addOrder(){
        long startTime = System.currentTimeMillis();
        for(int i=0;i<2000;i++){
            ArrayList<Order> orderList  = new ArrayList<>();
            for(int j=0;j<500;j++){
                Order order = new Order();
                order.setProductName(CharacterUtil.getRandomString(16));
                order.setOrderPoint(CharacterUtil.getRandomInt(10));
                order.setOrderTime(new Date());
                order.setDiscountAmount(CharacterUtil.getRandomDouble());
                order.setPaymentAmount(CharacterUtil.getRandomDouble());
                order.setPaymentTime(new Date());
                order.setPaymentMethod(CharacterUtil.getRandomInt(3));
                order.setProductNumber(CharacterUtil.getRandomInt(12));
                order.setProductPrice(CharacterUtil.getRandomDouble());
                orderList.add(order);
            }
            orderDAO.batchAddOrder(orderList);
        }
        long endTime = System.currentTimeMillis();
        long time = endTime-startTime;
        System.out.println("耗时："+time+"毫秒");
    }

}
