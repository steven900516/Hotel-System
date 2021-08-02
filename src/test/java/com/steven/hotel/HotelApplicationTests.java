package com.steven.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
class HotelApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;
    private String emailServiceCode;

    @Test
    void contextLoads() {
    }

    @Test

              public void test(){
                 emailServiceCode = "1234";
                 SimpleMailMessage message = new SimpleMailMessage();
                 message.setSubject("注册验证码");
                 message.setText("注册验证码是：" + emailServiceCode);
                 message.setFrom("1569132595@qq.com");
                 message.setTo("1569132595@qq.com");
                 mailSender.send(message);
             }

}
