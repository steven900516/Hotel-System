package com.steven.hotel.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steven.hotel.entity.User;
import com.steven.hotel.mapper.UserMapper;
import com.steven.hotel.service.Encoder.AESDecoder;
import com.steven.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author shkstart
 * @create 2021-07-25 11:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Override
    public User confirmOne(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();//条件构造器
        //开始构造条件
        //select * from admin where loginName = "admin" and password = "admin";
        wrapper.eq("username",user.getUsername());
        User one = userMapper.selectOne(wrapper);//one:  null    Admin{loginName,password}
        return one;
    }

    @Override
    public User userLogin(User user) {
        User user1 = confirmOne(user);
        if(user1 == null){
            return null;
        }else if(user1.getUsername().equals(user.getUsername()) &&
                user1.getPassword().equals(user.getPassword())){
            return user1;
        }
        return null;
    }

    @Override
    public Page<User> pageUsers(Page page) {
        return userService.page(page,null);
    }


}
