package com.service.impl;

import com.beans.OrderItem;
import com.dao.OrderItemDao;
import com.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/7/3   16:30
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemDao orderItemDao;
    @Override
    public List<OrderItem> getOrderItem(String orderId) {
        return orderItemDao.getOrderItems(orderId);
    }

    @Override
    public void saveOrderItem(List<OrderItem> orderItem) {
        orderItemDao.saveBatchOrderItem(orderItem);
    }
}
