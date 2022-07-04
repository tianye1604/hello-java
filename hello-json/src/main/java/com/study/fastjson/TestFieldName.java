package com.study.fastjson;

import com.alibaba.fastjson.JSON;
import com.study.dto.User;

public class TestFieldName {

    public static void main(String[] args) {
        User su = new User();
        su.setName("test");
        su.setNickName("dd");

        String s = JSON.toJSONString(su);
        System.out.println(s);

        User su2 = JSON.parseObject("{\"nickName\":\"dd\",\"name\":\"test\"}\n",User.class);
        System.out.println(JSON.toJSON(su2));

        String ss = "1111";
        System.out.println(JSON.toJSONString(ss));

    }
}
