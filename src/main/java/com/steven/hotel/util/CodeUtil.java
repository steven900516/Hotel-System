package com.steven.hotel.util;

import java.util.Random;

/**
 * @author shkstart
 * @create 2021-07-24 20:16
 */
public class CodeUtil {
    public static final String VERIFY_CODES = "1234567890";

    /**
     * 使用系统默认字符源生成验证码
     * @param verifySize    验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize){
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 使用指定源生成验证码
     * @param verifySize    验证码长度
     * @param sources   验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
    }

}
