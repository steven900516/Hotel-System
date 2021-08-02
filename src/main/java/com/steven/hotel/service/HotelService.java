package com.steven.hotel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.hotel.entity.Hotel;
import com.steven.hotel.entity.User;

import java.awt.print.Book;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-07-26 23:54
 */

public interface HotelService extends IService<Hotel> {
    public List<Hotel> findHotel(String hotel_name);

    IPage<Hotel> hotelPage(Page<Hotel> page, Hotel hotel);

    public Page listAllHotel(Page<Hotel> page);
}
