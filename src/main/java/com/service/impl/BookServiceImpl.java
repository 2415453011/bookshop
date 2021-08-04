package com.service.impl;

import com.beans.Book;
import com.beans.Page;
import com.dao.BookDao;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/26   10:45
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBookId(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookId(id);
    }

    @Override
    public List<Book> queryAllBook() {

        return bookDao.queryBooks();
    }

    @Autowired
    private Page<Book> page;
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        page.setShowTotal(pageSize);// 设置每页显示的数量
        Integer pageTotalCount = bookDao.queryForPageTotalCount();// 求总记录数
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
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);

        page.setItem(items);//设置当前页数据
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
// 设置每页显示的数量
        page.setShowTotal(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        // 设置当前页数据
        page.setItem(items);
        return page;
    }
}
