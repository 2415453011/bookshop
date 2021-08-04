package com.controller;

import com.beans.*;
import com.service.OrderItemService;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Xjm
 * @date 2021/7/3   16:32
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderService orderService;


    @RequestMapping("manager/orderPage.do")
    public String orderPageManager(@RequestParam(defaultValue = "1") Integer pageNo,
                           @RequestParam(defaultValue = "4") Integer pageSize,
                           Model model){
        Page<Order> page = orderService.orderPage(pageNo,pageSize);
        page.setUrl("order/manager/orderPage.do");
        model.addAttribute("page",page);
        return "forward:/pages/manager/order_manager.jsp";
    }

    @RequestMapping("manager/sendOder.do")
    public String sendOder(HttpServletRequest request,String orderId){
        orderService.sendOrder(orderId,1);
        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping("manager/listOrder.do")
    public String listOrderManager(Model model){
        List<Order> allOrder = orderService.getAllOrder();
        model.addAttribute("allOrder",allOrder);
        return "forward:/pages/manager/order_manager.jsp";
    }

    @RequestMapping("client/receiveOrder.do")
    public String receiveOrder(HttpServletRequest request,String orderId){
        orderService.sendOrder(orderId,2);
        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping("client/orderDetail.do")
    public String orderDetail(String orderId, Model model) {
        List<OrderItem> orderItem = orderItemService.getOrderItem(orderId);
        model.addAttribute("orderItem", orderItem);
        return "forward:/pages/order/order_detail.jsp";
    }

    @RequestMapping("client/listOrder.do")
    public String listOrder(HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null){
            request.setAttribute("msg","此操作需要登陆，请先登陆");
            return "forward:/pages/user/login.jsp";
        }
        List<Order> myOrder = orderService.getMyOrder(user.getId());
        request.setAttribute("myOrder",myOrder);

        return "forward:/pages/order/order.jsp";
    }


    @RequestMapping("client/creatOrder.do")
    public String creatOrder(HttpServletRequest request, HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        User loginUser = (User) session.getAttribute("user");

        if (loginUser == null){
            request.setAttribute("msg","此操作需要登陆，请先登陆");
            return "forward:/pages/user/login.jsp";
        }
        Integer userId = loginUser.getId();
        String orderId = orderService.createOrder(cart, userId);
        session.setAttribute("orderId",orderId);
        return "redirect:/pages/cart/checkout.jsp";
    }
}
