package com.wisdomelon.base.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.wisdomelon.base.utils.json.support.JsonMapper;

import java.io.IOException;

/**
 * @author wisdomelon
 * @date 2019/1/10 0010
 * @project fast
 * @jdk 1.8
 */
public class JsonUtils {

    public static JsonMapper jsonMapper = new JsonMapper();

    public static String bean2Json(Object o) {
        try {
            return jsonMapper.bean2Json(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T json2Bean(String jsonString, Class<T> c) {
        try {
            return jsonMapper.json2Bean(jsonString, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T json2CollectionBean(String jsonString, Class<?> collectionClass, Class<?>... elementClass) {
        try {
            return jsonMapper.json2Bean(jsonString, collectionClass, elementClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T json2Bean(String jsonString, JavaType javaType) {
        try {
            return jsonMapper.json2Bean(jsonString, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
