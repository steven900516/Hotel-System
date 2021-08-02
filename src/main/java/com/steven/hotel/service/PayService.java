package com.steven.hotel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.hotel.entity.Pay;
import com.steven.hotel.entity.User;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-07-27 23:19
 */

public interface PayService extends IService<Pay> {
    public Page<Pay> listPay(Page page,Pay pay);
    public Page<Pay> allPay(Page page);
    public Page<Pay> unPay(Page page);
}
