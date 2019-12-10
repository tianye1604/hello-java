package com.study.anything;

import java.lang.reflect.Array;

/**
 * Created by tianshujian
 * Create Date: 2019/12/10 14:14
 * Description: ${DESCRIPTION}
 */
public class TestGetCanonicalName {
    public static void main(String[] args) {
        System.out.println("Java8Test.getName: " + Java8Test.class.getName());
        System.out.println("Java8Test.getCanonicalName: " + Java8Test.class.getCanonicalName());


        System.out.println("String.getName: " + String.class.getName());
        System.out.println("String.getCanonicalName: " + String.class.getCanonicalName());

        String[] str = {"a","b"};
        System.out.println("String[].getName: " + str.getClass().getName());
        System.out.println("String[].getCanonicalName: " + str.getClass().getCanonicalName());

        int[] arr = new int[10];
        System.out.println("int[].getName: " + arr.getClass().getName());
        System.out.println("int[].getCanonicalName: " + arr.getClass().getCanonicalName());

        Integer[][] arr2 = {{1,2,3},{1,3,2}};
        System.out.println("Integer[][].getName: " + arr2.getClass().getName());
        System.out.println("Integer[][].getCanonicalName: " + arr2.getClass().getCanonicalName());
    }
}
