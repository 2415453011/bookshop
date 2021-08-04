package com.dao;

import com.beans.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/24   12:01
 */
@Repository
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果用户名返回null，说明没有这个用户
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @return 如果用户名返回null，说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    /**
     * 保存用户信息
     * @param user 用户名
     * @return 返回-1表示操作失败，其他是影响行数
     */
    public int saveUser(User user);

    List<User> selectUser();
}
