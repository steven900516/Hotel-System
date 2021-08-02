package com.steven.hotel;

import com.steven.hotel.entity.User;
import com.steven.hotel.service.Encoder.AESDecoder;
import com.steven.hotel.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author shkstart
 * @create 2021-07-25 11:53
 */
public class MybatisPlusTest {
    @Test
    public void Test(){
        System.out.println(AESDecoder.decrypt4Aes2Str("123","CPnb66hrzjgzKfGoTbXgndWG"));
    }
}
