package cn.aouo.sys.service;

import cn.aouo.sys.entity.User;

import java.util.List;

/**
 * Created by USER on 2018/7/25.
 */
public interface UserService {

    User getUserById(Integer id);

    public List<User> getUserList();

    public int add(User user);

    public int update(Integer id, User user);

    public int delete(Integer id);
}
