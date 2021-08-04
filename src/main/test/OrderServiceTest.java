import com.beans.Cart;
import com.beans.Order;
import com.beans.Page;
import com.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/7/4   13:19
 */
public class OrderServiceTest extends BaseTest {
    @Autowired
    private OrderService orderService;
    @Test
    public void test01(){
        List<Order> myOrder = orderService.getMyOrder(1);
        for (Order order : myOrder){
            System.out.println(order);
        }
    }
    @Test
    public void test02(){
        List<Order> myOrder = orderService.getAllOrder();
        for (Order order : myOrder){
            System.out.println(order);
        }
    }
    @Test
    public void test03(){
        orderService.sendOrder("123",1);
    }
    @Test
    public void test04(){
        Cart cart = new Cart();
        orderService.createOrder(cart,1);
    }
    @Test
    public void test05(){
        Page<Order> orderPage = orderService.orderPage(1, 4);
        List<Order> item = orderPage.getItem();
        for (Order order : item){
            System.out.println(item);

        }

    }
}
