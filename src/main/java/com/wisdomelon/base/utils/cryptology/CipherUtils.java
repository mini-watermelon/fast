package com.wisdomelon.base.utils.cryptology;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class CipherUtils {

    /**
     * 加密方法
     * @param strKey
     * @param strIn
     * @return
     * @throws Exception
     */
    public static String encrypt(String strKey, String strIn) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(strIn.getBytes("UTF-8"));
        String value = new BASE64Encoder().encode(encrypted);
        value = value.replaceAll("\\+", "%2B");
        value = value.replaceAll("\\/", "010010");
        return value;
    }

    /**
     * 解密方法
     * @param strKey
     * @param strIn
     * @return
     * @throws Exception
     */
    public static String decrypt(String strKey, String strIn) throws Exception {
        strIn = strIn.replaceAll("%2B", "\\+");
        strIn = strIn.replaceAll("010010", "\\/");
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1 = new BASE64Decoder().decodeBuffer(strIn);

        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original,"UTF-8");
        return originalString;
    }

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }
}
