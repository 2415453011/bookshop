package com.controller;

import com.beans.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Xjm
 * @date 2021/6/24   12:49
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("/regist.do")
    public ModelAndView regist(HttpServletRequest req,User user){
//        获取session中的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");
        ModelAndView modelAndView = new ModelAndView();
        String tips = "";
        //        2、检查 验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(user.getUsername())) {
                System.out.println("用户名[" + user.getUsername() + "]已存在!");

                // 把回显信息，保存到Request域中
                req.setAttribute("msg", "用户名已存在！！");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("email",user.getEmail());
//        跳回注册页面
                modelAndView.setViewName("forward:/pages/user/regist.jsp");
            } else {
                //      可用
//                调用Sservice保存到数据库
                userService.registUser(user);
                tips = "用户【" + user.getUsername() + "】注册成功！";
                modelAndView.addObject("tips",tips);
//        跳到注册成功页面 regist_success.jsp
                modelAndView.setViewName("forward:/pages/user/regist_success.jsp");
            }
        } else {
            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "验证码错误！！");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());
            System.out.println("验证码[" + code + "]错误");
            modelAndView.setViewName("forward:/pages/user/regist.jsp");
        }
        return modelAndView;
    }

    /**
     * 登录
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request,User user){
        String token = (String)request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = request.getParameter("code");
        request.setAttribute("username",user.getUsername());
        request.setAttribute("password",user.getPassword());
        User login = userService.login(user);
        if (token != null && token.equalsIgnoreCase(code)) {
            if (login == null){
                request.setAttribute("msg", "用户名或密码错误！");
                return "forward:/pages/user/login.jsp";
            }
            request.getSession().setAttribute("user",login);
        }else {
            request.setAttribute("msg", "验证码错误！！");
            System.out.println("验证码[" + code + "]错误");
            return "forward:/pages/user/login.jsp";
        }
        return "forward:/pages/user/login_success.jsp";
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.jsp";
    }

    @RequestMapping("/checkout.do")
    @ResponseBody
    public boolean check(String username){
        boolean flag = userService.existsUsername(username);
        if (flag){
            return true;
        }
        return false;
    }


}
