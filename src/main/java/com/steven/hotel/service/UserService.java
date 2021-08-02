package com.steven.hotel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.hotel.entity.User;
import org.springframework.stereotype.Service;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author shkstart
 * @create 2021-07-25 11:32
 */


public interface UserService extends IService<User> {
    public User confirmOne(User user);
    public User userLogin(User user);
    public Page<User> pageUsers(Page page);
}
