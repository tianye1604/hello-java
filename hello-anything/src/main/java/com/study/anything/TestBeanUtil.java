package com.study.anything;

import com.jd.jsf.gd.util.BeanUtils;
import com.jd.org.apache.harmony.beans.BeansUtils;
import com.study.anything.model.UserInfo;

public class TestBeanUtil {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setBizUserPin("tina");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserId(2L);
        userInfo2.setShopId(100L);

        BeanUtils.copyProperties(userInfo,userInfo2);
        System.out.println(userInfo2);

    }
}
