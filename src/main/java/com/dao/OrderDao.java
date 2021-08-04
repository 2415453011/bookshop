package com.dao;

import com.beans.Book;
import com.beans.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/7/3   16:48
 */
@Repository
public interface OrderDao {
    //    保存订单
    public int saveOrder(Order order);

    //    修改订单状态
    public int updateStatus(Order order);

    //    查出所有订单（管理员）
    public List<Order> getOrderList();

    //    查出某个用户的所有订单
    public List<Order> getOrderByUserId(Integer userId);

    Integer queryForPageTotalCountOrder();//查询总记录数

    List<Order> queryForPageOrder(@Param(value = "begin") int begin, @Param(value = "pageSize") int pageSize);
}
