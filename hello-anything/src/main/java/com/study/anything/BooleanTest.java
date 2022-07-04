package com.study.anything;

import java.util.Locale;

/**
 * Created by tianshujian
 * Create Date: 2019/12/12 9:55
 * Description: ${DESCRIPTION}
 */
public class BooleanTest {

	public static void main(String[] args) {
//		Boolean a = false, b = false, c = true, d = true;
//		System.out.println(a == b);
//		System.out.println(c == d);

		String sql = "1111limit ;";
		System.out.println(sql.indexOf("limit") > -1);

		String sqlLimit = sql.toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
		String SQL_REGEX = " limit ";
		System.out.println(sqlLimit.matches(SQL_REGEX));
		if(sqlLimit.indexOf(";") > -1) {
			String subSQL = sqlLimit.substring(sqlLimit.indexOf(";") + 1);
			System.out.println("[" + subSQL + "]");

			System.out.println(sqlLimit.substring(0,sqlLimit.indexOf(";")));
		}




	}
}
