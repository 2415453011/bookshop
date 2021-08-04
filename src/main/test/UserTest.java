import com.beans.User;
import com.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Xjm
 * @date 2021/6/24   12:27
 */
public class UserTest extends BaseTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void test01(){
        int i = userDao.saveUser(new User(null,"xjm","xjm","xjm@qq.com"));
        System.out.println(i);
    }
    @Test
    public void test02(){
        User user = userDao.queryUserByUsername("a");
        System.out.println(user);
    }
    @Test
    public void test03(){
        User user = userDao.queryUserByUsernameAndPassword("jm","xjm");
        System.out.println(user);
    }
}
