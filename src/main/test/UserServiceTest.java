import com.beans.User;
import com.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/24   12:50
 */
public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService userService;
    @Test
    public void test01(){
        userService.registUser(new User(null,"hy","hy","hy@qq.com"));
    }
    @Test
    public void test02(){
        boolean xjm = userService.existsUsername("jm");
        if (xjm == true){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
    @Test
    public void test03(){
        User login = userService.login(new User(null, "xjm", "xjm", "xjm@qq.com"));
        System.out.println(login);
    }
    @Test
    public void test04(){
        List<User> users = userService.queryUser();
        for (User user : users){
            System.out.println(user);
        }
    }
}
