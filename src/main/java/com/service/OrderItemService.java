package com.service;

import com.beans.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/7/3   16:30
 */
@Service
public interface OrderItemService {
    /**
     * 获取订单所有项
     * @param orderId
     * @return
     */
    public List<OrderItem> getOrderItem(String orderId);

    /**
     * 保存订单项
     * @param orderItem
     */
    public void saveOrderItem(List<OrderItem> orderItem);
}
