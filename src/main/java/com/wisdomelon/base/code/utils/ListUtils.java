package com.wisdomelon.base.code.utils;

import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class ListUtils {

    public static String listToStr(List<String> list){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<list.size(); i++){
            if (i != list.size()-1){
                sb.append(list.get(i)).append(",");
            } else {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }
}
