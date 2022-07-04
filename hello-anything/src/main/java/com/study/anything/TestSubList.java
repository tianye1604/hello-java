package com.study.anything;

import org.junit.platform.commons.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class TestSubList {
    public static final String MOBILE_REG =  "^1[0-9]{10}$";

    public static void main(String[] args) {
//        List<String> stringList = new ArrayList<>();
//        stringList.add("1");
//        stringList.add("2");
//
//        stringList.add("3");
//
//        stringList.add("4");
//
//        List<String> subList = stringList.subList(1,stringList.size());
//
//        System.out.println(subList.size());
//
//        List<Object> objList = Collections.singletonList(null);
//        System.out.println(objList);
//
//        boolean matches = Pattern.matches(MOBILE_REG, "17098330689");
//        System.out.println(matches);

        List<Object> objList = new ArrayList<>();

        System.out.println(objList.get(0));

    }
}
