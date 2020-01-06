package com.study.anything;

import java.util.Calendar;

/**
 * Created by tianshujian
 * Create Date: 2019/12/10 20:51
 * Description: ${DESCRIPTION}
 */
public class TestCalender {

    public static void main(String[] args) {
        Calendar cale = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append(cale.get(Calendar.YEAR));
        int month = cale.get(Calendar.MONTH)+1;
        String monthStr = month < 10 ? "0"+month : month+"";
        sb.append(monthStr);
        int date = cale.get(Calendar.DAY_OF_MONTH);
        String dateStr = date < 10 ? "0"+date : date+"";
        sb.append(dateStr);
        System.out.println(sb.toString());

    }
}
