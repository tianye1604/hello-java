package com.study.anything;

import java.util.UUID;

public class TestUUID {

    public static void main(String[] args) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(token);
        System.out.println(token.length());
    }
}
