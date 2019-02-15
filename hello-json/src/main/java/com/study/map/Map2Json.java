package com.study.map;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/2/12 15:29
 * @Description:
 */
public class Map2Json {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("text",1);
        map.put("image",2);
        map.put("media",1);
        System.out.println(JSON.toJSONString(map));

        Set<Map.Entry<String,Integer>> typeSet = map.entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : typeSet) {
            String key = (String)entry.getKey();
            Integer value = (Integer) entry.getValue();
            sb.append(value);
            sb.append(" ");
            sb.append(key);
            sb.append(",");
        }
        String str = sb.substring(0,sb.lastIndexOf(","));

        System.out.println(str);

    }
}
