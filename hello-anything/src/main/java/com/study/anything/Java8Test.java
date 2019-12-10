package com.study.anything;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by tianshujian
 * Create Date: 2019/12/6 20:11
 * Description: ${DESCRIPTION}
 */
public class Java8Test {
    public static void main(String[] args) {

        List<String> names1 = new ArrayList<>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = Arrays.asList("Google ","Runoob","Taobao ","Baidu ","Sina ");

        sortUsingJava7(names1);
        names1.forEach(System.out::println);
        System.out.println("------------------");
        sortUsingJava8(names2);
        names1.forEach(System.out::println);

    }

    /**
     * 使用 java7 排序
     * @param names
     */
    private static void sortUsingJava7(List<String> names){
        Collections.sort(names, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * 使用 java8 排序
     * @param names
     */
    private static void sortUsingJava8(List<String> names) {
        Collections.sort(names,(o1,o2) -> o1.compareTo(o2) );
    }


    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list) {

            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }
}
