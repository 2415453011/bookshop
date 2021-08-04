package com.dao;

import com.beans.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/7/3   16:01
 */
@Repository
public interface OrderItemDao {
    //    批量保存订单项
    public int saveBatchOrderItem(List<OrderItem> params);

    //    保存订单项
    public int saveOrderItem(OrderItem orderItem);

    //    根据订单号查询订单项详情
    public List<OrderItem> getOrderItems(String orderId);
}
