import com.beans.Book;
import com.dao.BookDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/24   11:10
 */
public class BookTest extends BaseTest {
    @Autowired
    private BookDao bookDao;
    @Test
    public void test01(){
        List<Book> books = bookDao.queryBooks();
        for (Book book : books){
            System.out.println(book);
        }
    }
    @Test
    public void test02(){
        Book book = bookDao.queryBookId(2);
        System.out.println(book);

    }
    @Test
    public void test03(){
        Book book = new Book();
        book.setName("xjm");
        book.setAuthor("xjm");
        book.setPrice(new BigDecimal(99.9));
        book.setSales(123);
        book.setStock(99999);
        book.setImg_path("1");
        int i = bookDao.addBook(book);
        System.out.println(i);
    }
    @Test
    public void test04(){
        int i = bookDao.deleteBookId(21);
        System.out.println(i);
    }
    @Test
    public void test05(){
        int i = bookDao.updateBook(new Book(22,"xjm","徐佳明",new BigDecimal(11.11),999,9999,"1"));
        System.out.println(i);
    }
    @Test
    public void test06(){
        Integer integer = bookDao.queryForPageTotalCount();
        System.out.println(integer);
    }
    @Test
    public void test07(){
        List<Book> books = bookDao.queryForPageItems(0, 3);
        for (Book book : books){
            System.out.println(book);
        }
    }
    @Test
    public void test08(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(20,100));
    }
    @Test
    public void test09(){
        List<Book> books = bookDao.queryForPageItemsByPrice(0, 4, 0, 1000);
        for (Book book : books){
            System.out.println(book);
        }
    }
}
