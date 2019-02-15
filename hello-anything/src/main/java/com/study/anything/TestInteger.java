package com.study.anything;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/2/15 14:04
 * @Description:
 * 【强制】所有的相同类型的包装类对象之间值的比较，全部使用 equals 方法比较。
 * 说明：对于 Integer var = ? 在-128 至 127 范围内的赋值，Integer 对象是在
 * IntegerCache.cache 产生，会复用已有对象，这个区间内的 Integer 值可以直接使用==进行
 * 判断，但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象，这是一个大坑，
 * 推荐使用 equals 方法进行判断。
 */
public class TestInteger {
	public static void main(String[] args) {
		Integer a = 128;
		Integer b = 128;
		Boolean f1 = a == b;
		Boolean f2 = a.equals(b);
		System.out.println("f1: " + f1); // false
		System.out.println("f2: " + f2); // true
		Integer c = 127;
		Integer d = 127;
		Boolean f3 = c == d;
		Boolean f4 = c.equals(d);
		System.out.println("f3: " + f3); // true
		System.out.println("f4: " + f4); // true
	}
}
