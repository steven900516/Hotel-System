package com.steven.hotel.service.Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author shkstart
 * @create 2021-07-25 11:50
 */
public class AESDecoder {
    public static String decrypt4Aes2Str(String contentbase64 ,String aesKey) {

        String Result = null;

        try {

            byte[] dst = decrypt4Aes(contentbase64 ,aesKey);

            if (null != dst) {

                Result = new String(dst, "UTF-8");

            }

        } catch (Exception e3) {

        }

        return Result;

    }

    public static byte[] decrypt4Aes(String contentbase64 ,String aesKey) {

        try {

            byte[] src = base64decode(contentbase64);


            return decryptMode(src ,aesKey);

        } catch (Exception e3) {

        }

        return null;

    }

    public static byte[] decryptMode(byte[] src ,String aesKey) {

        try {

            Cipher cip = Cipher.getInstance("AES");

            cip.init(Cipher.DECRYPT_MODE, getSecretKey(aesKey));

            return cip.doFinal(src);

        } catch (Exception e3) {

        }

        return null;

    }


    public static byte[] base64decode(String s) {

        if (s == null)
            return null;

        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

        try {

            byte[] b = decoder.decodeBuffer(s);

            return b;

        } catch (Exception e) {

            return null;

        }

    }

    public static SecretKey getSecretKey(String aesKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        byte[] keybyte = getKeyByStr(aesKey);// toReplace

        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

        secureRandom.setSeed(keybyte);

        KeyGenerator keygen = KeyGenerator.getInstance("AES");

        keygen.init(secureRandom);

        return keygen.generateKey();

    }

    public static byte[] getKeyByStr(String str) {

        byte[] bRet = new byte[str.length() / 2];

        for (int i = 0; i < str.length() / 2; i++) {

            Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i)) + getChrInt(str.charAt(2 * i + 1)));

            bRet[i] = itg.byteValue();

        }

        return bRet;

    }

    public static int getChrInt(char chr) {

        int iRet = 0;

        if (chr == "0".charAt(0))
            iRet = 0;

        if (chr == "1".charAt(0))
            iRet = 1;

        if (chr == "2".charAt(0))
            iRet = 2;

        if (chr == "3".charAt(0))
            iRet = 3;

        if (chr == "4".charAt(0))
            iRet = 4;

        if (chr == "5".charAt(0))
            iRet = 5;

        if (chr == "6".charAt(0))
            iRet = 6;

        if (chr == "7".charAt(0))
            iRet = 7;

        if (chr == "8".charAt(0))
            iRet = 8;

        if (chr == "9".charAt(0))
            iRet = 9;

        if (chr == "A".charAt(0))
            iRet = 10;

        if (chr == "B".charAt(0))
            iRet = 11;

        if (chr == "C".charAt(0))
            iRet = 12;

        if (chr == "D".charAt(0))
            iRet = 13;

        if (chr == "E".charAt(0))
            iRet = 14;

        if (chr == "F".charAt(0))
            iRet = 15;

        return iRet;

    }

}
