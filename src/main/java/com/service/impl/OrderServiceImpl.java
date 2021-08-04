package com.service.impl;

import com.beans.*;
import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.OrderItemDao;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Xjm
 * @date 2021/7/3   17:07
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Order> getMyOrder(Integer userId) {
        return orderDao.getOrderByUserId(userId);
    }

    @Override
    public List<Order> getAllOrder() {

        return orderDao.getOrderList();
    }

    @Override
    public void sendOrder(String orderId, Integer status) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(status);
        orderDao.updateStatus(order);
    }

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
//            获取每个购物车中的商品项
            CartItem cartItem = entry.getValue();
//            转化为订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getPrice(),cartItem.getTotalPrice(),cartItem.getCount(),orderId);
//            保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

//            更新库存和销量
            Book book = bookDao.queryBookId(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        System.out.println(orderId);
        return orderId;
    }

    @Autowired
    private Page<Order> page;
    @Override
    public Page<Order> orderPage(int pageNo, int pageSize) {
        page.setShowTotal(pageSize);// 设置每页显示的数量
        Integer pageTotalCount = orderDao.queryForPageTotalCountOrder();// 求总记录数
        page.setPageTotalCount(pageTotalCount);// 设置总记录数
        Integer pageTotal = pageTotalCount / pageSize;// 求总页码
        if (pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);//设置总页码
        page.setPageNo(pageNo);//设置当前页码

        // 求当前页数据的开始索引
        Integer begin = (page.getPageNo() - 1) * pageSize;

        // 求当前页数据
        List<Order> items = orderDao.queryForPageOrder(begin, pageSize);

        page.setItem(items);//设置当前页数据
        return page;
    }
}
