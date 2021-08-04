import com.beans.OrderItem;
import com.dao.OrderItemDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author Xjm
 * @date 2021/7/3   16:11
 */
public class OrderItemDaoTest extends BaseTest {
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private OrderItem orderItem;
    @Test
    public void test01(){
        orderItemDao.saveOrderItem(new OrderItem(null,"dasd",new BigDecimal(123),new BigDecimal(1111),
                1231,"123"));
    }
    @Test
    public void test0(){
        System.out.println(orderItemDao.getOrderItems("123"));
    }
}
