package com.wisdomelon.test.utils;

import com.wisdomelon.base.utils.javascript.EscapeUtils;
import org.junit.Test;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class TestJs {

    @Test
    public void test1() {
        String escape = EscapeUtils.escape("中文");
        System.out.println(escape);

        String unescape = EscapeUtils.unescape(escape);
        System.out.println(unescape);
    }
}
