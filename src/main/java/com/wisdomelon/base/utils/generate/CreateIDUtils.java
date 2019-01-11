package com.wisdomelon.base.utils.generate;

import java.util.UUID;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class CreateIDUtils {

    /***
     * 取得UUID唯一序列号（长度为36）
     * @example 974c2281-5012-490e-b99c-799069f26aef
     * @return
     */
    public static String getUniqueID() {
        return getUUID();
    }

    /***
     * 取得指定前缀的UUID唯一序列号（长度为不固定，至少为36）
     * @param prefix
     * @return
     */
    public static String getUniqueID(String prefix) {
        if ("".equals(prefix) || null == prefix) {
            return getUUID();
        }
        return prefix + getUUID();
    }

    /***
     * 取得UUID唯一序列号，过滤"-"（长度为32）
     * @example 239e5600f85044eda07bef5824b526c3
     * @return
     */
    public static String getFilterUnqiueID() {
        return getUUID().replace("-","");
    }

    /***
     * 取得UUID唯一序列号，过滤"-"（长度为不固定，至少为32）
     * @example 239e5600f85044eda07bef5824b526c3
     * @return
     */
    public static String getFilterUnqiueID(String prefix) {
        if ("".equals(prefix) || null == prefix) {
            return getUUID().replace("-","");
        }
        return prefix + getUUID().replace("-","");
    }

    /***
     * 取得UUID唯一序列号（长度为36）
     * @example 974c2281-5012-490e-b99c-799069f26aef
     * @return
     */
    private static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
