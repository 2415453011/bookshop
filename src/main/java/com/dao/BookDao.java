package com.dao;

import com.beans.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/23   22:05
 */
@Repository
public interface BookDao {
    int addBook(Book book);//添加图书

    int deleteBookId(Integer id);//删除图书

    int updateBook(Book book);//更新图书

    Book queryBookId(Integer id);//通过id查询图书

    List<Book> queryBooks();//查询图书

    Integer queryForPageTotalCount();//查询总记录数

    //查询商品项
    List<Book> queryForPageItems(@Param(value = "begin") int begin, @Param(value = "pageSize") int pageSize);

    Integer queryForPageTotalCountByPrice(@Param(value = "min")int min,@Param(value = "max")int max);//价格区间查询

    List<Book> queryForPageItemsByPrice(@Param(value = "begin") int begin,@Param(value = "pageSize") int pageSize,
                                        @Param(value = "min")int min, @Param(value = "max")int max);
}
