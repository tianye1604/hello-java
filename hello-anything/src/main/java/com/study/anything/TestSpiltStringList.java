package com.study.anything;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSpiltStringList {
    public static void main(String[] args) {

        List<String> roleCodeList = new ArrayList<>();
        roleCodeList.add("123");
        roleCodeList.add("abc,def");
        roleCodeList.add("456");

        List<String> roleCodeList2 = new ArrayList<>();
        roleCodeList.forEach(o-> {
            if(o.indexOf(",") > 0) {
                String [] codes = o.split(",");
                roleCodeList2.addAll(Arrays.asList(codes));
            } else {
                roleCodeList2.add(o);
            }
        });

        System.out.println(roleCodeList2);
    }
}
