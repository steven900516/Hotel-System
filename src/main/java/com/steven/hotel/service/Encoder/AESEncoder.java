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
 * @create 2021-07-25 11:46
 */
public class AESEncoder {
    public static String encrypt4Aes(String content, String aesKey) {

        try {
            byte[] src = content.getBytes("UTF-8");
            byte[] bytOut = encryptMode(src, aesKey);
            return base64encode(bytOut);

        } catch (Exception e3) {

        }

        return null;

    }
    public static byte[] encryptMode(byte[] src, String aesKey) {

        try {

            Cipher cip = Cipher.getInstance("AES"); //wBk0G/UM+QvU4QYS0JIZKw==

            cip.init(Cipher.ENCRYPT_MODE, getSecretKey(aesKey));

            return cip.doFinal(src);

        } catch (Exception e3) {

        }

        return null;

    }
    public static String base64encode(byte[] src) {

        if (src == null) {
            return null;
        }

        return (new sun.misc.BASE64Encoder()).encode(src);

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

        if (chr == "0".charAt(0)) {
            iRet = 0;
        }

        if (chr == "1".charAt(0)) {
            iRet = 1;
        }

        if (chr == "2".charAt(0)) {
            iRet = 2;
        }

        if (chr == "3".charAt(0)) {
            iRet = 3;
        }

        if (chr == "4".charAt(0)) {
            iRet = 4;
        }

        if (chr == "5".charAt(0)) {
            iRet = 5;
        }

        if (chr == "6".charAt(0)) {
            iRet = 6;
        }

        if (chr == "7".charAt(0)) {
            iRet = 7;
        }

        if (chr == "8".charAt(0)) {
            iRet = 8;
        }

        if (chr == "9".charAt(0)) {
            iRet = 9;
        }

        if (chr == "A".charAt(0)) {
            iRet = 10;
        }

        if (chr == "B".charAt(0)) {
            iRet = 11;
        }

        if (chr == "C".charAt(0)) {
            iRet = 12;
        }

        if (chr == "D".charAt(0)) {
            iRet = 13;
        }

        if (chr == "E".charAt(0)) {
            iRet = 14;
        }

        if (chr == "F".charAt(0)) {
            iRet = 15;
        }

        return iRet;

    }

}
