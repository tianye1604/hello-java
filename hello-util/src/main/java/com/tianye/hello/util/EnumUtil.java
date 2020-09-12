package com.tianye.hello.util;

import com.tianye.hello.util.enums.BankEnum;

import java.util.ArrayList;
import java.util.List;

public class EnumUtil {

    public static <T extends BankEnum> T getByName(String name, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (name.equals(each.getName())) {
                return each;
            }
        }
        return null;
    }


    public static <T extends BankEnum> String[] getNames(Class<T> enumClass) {
        List<String> nameList = new ArrayList<>();
        for (T each: enumClass.getEnumConstants()) {
            nameList.add(each.getName());
        }
        return nameList.toArray(new String[nameList.size()]);
    }
}