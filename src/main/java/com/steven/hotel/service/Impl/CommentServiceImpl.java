package com.steven.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steven.hotel.entity.Comment;
import com.steven.hotel.entity.Hotel;
import com.steven.hotel.mapper.CommentMapper;
import com.steven.hotel.mapper.HotelMapper;
import com.steven.hotel.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @author shkstart
 * @create 2021-07-29 17:24
 */

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
