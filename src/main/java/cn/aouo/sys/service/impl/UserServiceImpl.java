package cn.aouo.sys.service.impl;

import cn.aouo.common.util.EncoderHandler;
import cn.aouo.common.util.UUIDUtil;
import cn.aouo.sys.entity.User;
import cn.aouo.sys.mapper.UserMapper;
import cn.aouo.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by USER on 2018/7/25.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int update(Integer id, User user) {
        return userMapper.update(id,user);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public List<User> getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public int register(User user) {
        user.setId(UUIDUtil.getUUID());
        user.setPassword(EncoderHandler.encodeByMD5(user.getPassword()));
//        user.setCreateDate(new Date());
        return userMapper.register(user);
    }
}
