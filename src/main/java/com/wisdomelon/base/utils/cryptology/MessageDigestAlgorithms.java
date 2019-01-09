/*
 * 字符加密处理类，引入 apache commons codec 1.1
 */

package com.wisdomelon.base.utils.cryptology;

/**
 * 
 * 【服务端】密码加密工具类
 * <p>
 * 
 * 【功能描述】
 * 
 * <pre>
 *  1.提供MD5方式的密码加密
 * </pre>
 * 
 * 【特殊说明】
 * 
 * <pre>
 * 无
 * </pre>
 * 
 * @author 数据服务平台
 * @version 1.0, 01/22/16
 * 
 */
public class MessageDigestAlgorithms {

    private MessageDigestAlgorithms() {}

    /**
     * The MD2 message digest algorithm defined in RFC 1319.
     */
    public static final String MD2 = "MD2";

    /**
     * The MD5 message digest algorithm defined in RFC 1321.
     */
    public static final String MD5 = "MD5";

    /**
     * The SHA-1 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_1 = "SHA-1";

    /**
     * The SHA-256 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_256 = "SHA-256";

    /**
     * The SHA-384 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_384 = "SHA-384";

    /**
     * The SHA-512 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_512 = "SHA-512";

}
