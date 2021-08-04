package com.controller;

import com.beans.Book;
import com.beans.Page;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/26   14:15
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("manager/list.do")
    public String list(Model model){
        List<Book> books = bookService.queryAllBook();
        model.addAttribute("books",books);
        return "forward:/pages/manager/book_manager.jsp";
    }

    @RequestMapping("manager/page.do")
    public String page(@RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "4") Integer pageSize,
                       Model model){
        model.addAttribute("pageNo",pageNo);
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("book/page.do");
        model.addAttribute("page",page);
        return "forward:/pages/manager/book_manager.jsp";
    }

    @RequestMapping("manager/getBook.do")
    public String getBook(Model model,Integer id){
        Book book = bookService.queryBookById(id);
        model.addAttribute("book",book);
        return "forward:/pages/manager/book_edit.jsp";
    }


    @RequestMapping("manager/delete.do")
    public String delete(HttpServletRequest request,Model model, Integer id){
        bookService.deleteBook(id);
        return "redirect:/book/manager/page.do?pageNo=" + request.getParameter("pageNo");
    }

    @RequestMapping("manager/update.do")
    public String update(HttpServletRequest request,Book book){
        bookService.updateBook(book);
        return "redirect:/book/manager/page.do?pageNo=" + request.getParameter("pageNo");
    }

    @RequestMapping("manager/add.do")
    public String add(HttpServletRequest request,Book book){
        bookService.addBook(book);
        return "redirect:/book/manager/page.do?pageNo=" + request.getParameter("pageNo");
    }

    @RequestMapping("user/page.do")
    public String userPage(@RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "4") Integer pageSize,
                       Model model){
//        model.addAttribute("pageNo",pageNo);
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("book/user/page.do");
        model.addAttribute("page",page);
        return "forward:/pages/client/index.jsp";
    }

    @RequestMapping("user/pageByPrice.do")
    public String pageByPrice(@RequestParam(defaultValue = "1") Integer pageNo,
                              @RequestParam(defaultValue = "4") Integer pageSize,
                              @RequestParam(value = "min") Integer min,
                              @RequestParam(value = "max") Integer max,
                              Model model){
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        StringBuilder stringBuilder = new StringBuilder("book/user/page.do");
        if (min != null){
            stringBuilder.append("?min=").append(min);
        }
        if (max != null){
            stringBuilder.append("?max=").append(max);
        }
        page.setUrl(stringBuilder.toString());
        model.addAttribute("page",page);
        return "forward:/pages/client/index.jsp";
    }

}
