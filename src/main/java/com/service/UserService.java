package com.service;

import com.beans.User;

import java.util.List;

/**
 * @author Xjm
 * @date 2021/6/24   12:44
 */
public interface UserService {
    /**
     *注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果放回null则登录失败，反之成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，反之表示用户名可用
     */
    public boolean existsUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryUser();
}
