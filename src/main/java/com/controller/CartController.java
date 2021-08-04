package com.controller;

import com.beans.Book;
import com.beans.Cart;
import com.beans.CartItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.service.BookService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xjm
 * @date 2021/7/1   15:58
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private BookService bookService;
    @Autowired
    private Cart cart;
    @Autowired
    private CartItem cartItem;

    @RequestMapping("/update.do")
    public String update(HttpServletRequest request,Integer id,Integer count,HttpSession session){
        cart = (Cart)session.getAttribute("cart");
        if (cart != null){
            cart.updateItem(id,count);
        }
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/deleteCatItem.do")
    public String deleteCartItem(HttpServletRequest request,Integer id,HttpSession session){
        System.out.println(id);
        cart = (Cart) session.getAttribute("cart");
        cart.deleteItem(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/clear.do")
    public String clear(HttpServletRequest request,HttpSession session){
        cart = (Cart) session.getAttribute("cart");
        if (cart != null){
            cart.clear();
        }
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/addCart.do")
    @ResponseBody
    public void addCart(Integer id, HttpSession session, HttpServletResponse response) throws Exception {
        Book book = bookService.queryBookById(id);
        cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
            cart.addItem(cartItem);
        session.setAttribute("lastName",cartItem.getName());
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
//        Gson gson = new Gson();
//
//        String s = gson.toJson(resultMap);
//
//        response.getWriter().write(s);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(resultMap);
        System.out.println(s);
        PrintWriter writer = response.getWriter();
        writer.print(s);
        writer.flush();
        writer.close();
    }
}
