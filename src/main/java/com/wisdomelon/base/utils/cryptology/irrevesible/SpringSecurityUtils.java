package com.wisdomelon.base.utils.cryptology.irrevesible;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class SpringSecurityUtils {

    /***
     * MessageDigestPasswordEncoder加密，默认MD5
     * @param resource
     * @return
     */
    public static String encode(String resource){
        MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
        encoder.setEncodeHashAsBase64(true);

        return encoder.encodePassword(resource, null);
    }

    /***
     * MessageDigestPasswordEncoder加密
     * @param resource
     * @param encode
     * @return
     */
    public static String encode(String resource, String encode){
        MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder(encode);
        encoder.setEncodeHashAsBase64(true);

        return encoder.encodePassword(resource, null);
    }
}
