package com.wisdomelon.base.utils.cryptology.reversible;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class Base64Utils {

    /***
     * Base64加密
     * @param input
     * @return
     */
    public static String encode(String input) {
        return encode(input, "GBK");
    }

    /***
     * Base64加密
     * @param input
     * @param encode
     * @return
     */
    public static String encode(String input, String encode) {
        String encoded = "";
        try {
            byte[] outputBytes = input.getBytes(encode);
            BASE64Encoder encoder = new BASE64Encoder();
            encoded = encoder.encode(outputBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encoded;
    }

    /***
     * Base64解密
     * @param source
     * @return
     */
    public static String decode(String source) {
        return decode(source, "GBK");
    }

    /***
     * Base64解密
     * @param source
     * @param encode
     * @return
     */
    public static String decode(String source, String encode) {
        String decoded = "";
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] raw = decoder.decodeBuffer(source);
            decoded = new String(raw, encode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return decoded;
    }
}
