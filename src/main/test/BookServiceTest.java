import com.beans.Book;
import com.beans.Page;
import com.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/26   10:50
 */
public class BookServiceTest extends BaseTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private Page<Book> page;
    @Test
    public void test01(){
        bookService.addBook(new Book(null,"西游记","吴承恩",new BigDecimal(25.5),998,9999,"1"));
    }
    @Test
    public void test02(){
        bookService.deleteBook(23);
    }
    @Test
    public void test03(){
        bookService.updateBook(new Book(24,"三国","吴承恩",new BigDecimal(25.5),998,9999,"1"));
    }
    @Test
    public void test04(){
        Book book = bookService.queryBookById(24);
        System.out.println(book);
    }
    @Test
    public void test05(){
        List<Book> books = bookService.queryAllBook();
        for (Book book : books){
            System.out.println(book);
        }
    }
    @Test
    public void test06(){
        Page<Book> page = bookService.page(1, Page.PAGE_SIZE);
        List<Book> item = page.getItem();
        for (Book book : item){
            System.out.println(book);
        }
//        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
    @Test
    public void test07(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE, 0, 99));
    }
}
