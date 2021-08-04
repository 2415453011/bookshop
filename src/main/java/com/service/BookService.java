package com.service;

import com.beans.Book;
import com.beans.Page;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/26   10:41
 */
public interface BookService {
    void addBook(Book book);

    void deleteBook(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryAllBook();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
