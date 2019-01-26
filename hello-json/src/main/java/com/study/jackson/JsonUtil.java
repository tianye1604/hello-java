package com.study.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Objects;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/1/18 18:29
 * @Description:
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static GsonBuilder gsonBuilder = new GsonBuilder();

    public JsonUtil() {
    }

    public static String toJson(Object obj) {
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create().toJson(obj);
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        Objects.requireNonNull(json, "json is null.");
        Objects.requireNonNull(valueType, "value type is null.");

        try {
            return mapper.readValue(json, valueType);
        } catch (IOException var3) {
            throw new IllegalStateException("fail to convert [" + json + "] to [" + valueType + "].", var3);
        }
    }
}
