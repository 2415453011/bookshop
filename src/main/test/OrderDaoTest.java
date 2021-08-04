import com.beans.Order;
import com.dao.OrderDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Xjm
 * @date 2021/7/3   16:55
 */
public class OrderDaoTest extends BaseTest {
    @Autowired
    private OrderDao orderDao;
    @Test
    public void test01(){
        int i = orderDao.saveOrder(new Order("111333", new Date(), new BigDecimal(123), 2, 1));
        System.out.println(i);
    }
    @Test
    public void test02(){
        List<Order> orderByUserId = orderDao.getOrderByUserId(1);
        for (Order order : orderByUserId){
            System.out.println(order);
        }
    }
    @Test
    public void test03() {
        List<Order> orderList = orderDao.getOrderList();
        for (Order order : orderList) {
            System.out.println(order);
        }
    }
    @Test
    public void test04() {
        int i = orderDao.updateStatus(new Order("111333", new Date(), new BigDecimal(123), 11, 1));
        System.out.println(i);
    }
    @Test
    public void test05() {
        Integer integer = orderDao.queryForPageTotalCountOrder();
        System.out.println(integer);
    }
    @Test
    public void test06() {
        List<Order> orders = orderDao.queryForPageOrder(0, 4);
        for (Order order : orders){
            System.out.println(order);

        }
    }

}
