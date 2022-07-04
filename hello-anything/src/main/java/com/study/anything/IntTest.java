package com.study.anything;

import java.util.Objects;

/**
 * Created by tianshujian
 * Create Date: 2019/12/11 10:27
 * Description: ${DESCRIPTION}
 */
public class IntTest {
	public static void main(String[] args) {
		Integer a = 100, b = 100, c = 150, d = 150;
		System.out.println(a == b);
		System.out.println(c == d);
		System.out.println(c.equals(d));


		Float f = 2.0f;
		int v = f.intValue();
		int sort = Objects.isNull(f) ? 0: f.intValue();
		System.out.println(v);
		System.out.println(sort);


	}
}
