package com.steven.hotel.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steven.hotel.entity.Pay;
import com.steven.hotel.entity.User;
import com.steven.hotel.mapper.HotelMapper;
import com.steven.hotel.mapper.PayMapper;
import com.steven.hotel.mapper.UserMapper;
import com.steven.hotel.service.PayService;
import com.steven.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-07-27 23:20
 */


@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService {

    @Autowired
    private PayService payService;

    @Override
    public Page<Pay> listPay(Page page,Pay pay) {
        QueryWrapper<Pay> wrapper = new QueryWrapper<>();//条件构造器
        wrapper.eq("username",pay.getUsername());
        return payService.page(page,wrapper);
    }

    @Override
    public Page<Pay> allPay(Page page) {
        return payService.page(page,null);
    }

    @Override
    public Page<Pay> unPay(Page page) {
        QueryWrapper<Pay> wrapper = new QueryWrapper<>();
        wrapper.eq("pay",0);
        return payService.page(page,wrapper);
    }


}
