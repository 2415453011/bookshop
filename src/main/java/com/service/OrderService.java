package com.service;

import com.beans.Book;
import com.beans.Cart;
import com.beans.Order;
import com.beans.Page;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/7/3   17:06
 */
public interface OrderService {
    /**
     * 获取用户的所有订单
     * @param userId
     * @return
     */
    public List<Order> getMyOrder(Integer userId);
    /**
     * 获取所有订单（管理员使用）
     * @return
     */
    public List<Order> getAllOrder();
    /**
     * 发货，修改订单状态
     * @param orderId
     * @param status
     */
    public void sendOrder(String orderId,Integer status);

    /**
     * 保存订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart, Integer userId);

    /**
     * 订单分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Order> orderPage(int pageNo, int pageSize);
}
