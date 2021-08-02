package com.steven.hotel.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steven.hotel.entity.Hotel;
import com.steven.hotel.entity.User;
import com.steven.hotel.mapper.HotelMapper;
import com.steven.hotel.mapper.UserMapper;
import com.steven.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-07-27 0:04
 */

@Service
public class HotelServiceImpl  extends ServiceImpl<HotelMapper, Hotel> implements HotelService {



    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private HotelService hotelService;

    @Override
    public List<Hotel> findHotel(String hotel_name) {
        QueryWrapper<Hotel> wrapper = new QueryWrapper<>();//条件构造器

        wrapper.like("hotel_translated_name",hotel_name);
        List<Hotel> hotels = hotelMapper.selectList(wrapper);
        return hotels;
    }

    @Override
    public IPage<Hotel> hotelPage(Page<Hotel> page, Hotel hotel) {
        return hotelMapper.hotelPage(page,hotel);
    }

    @Override
    public Page listAllHotel(Page<Hotel> page) {
        return hotelService.page(page,null);
    }

}
