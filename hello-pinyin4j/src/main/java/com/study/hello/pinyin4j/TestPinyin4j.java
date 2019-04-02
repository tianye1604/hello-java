package com.study.hello.pinyin4j;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/2 14:59
 * @Description:
 */
public class TestPinyin4j {

	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		Pinyin4j pinyin4j = new Pinyin4j();
		String str = "重庆";
//		String first1 = pinyin4j.toPinYinUppercase(str);
//		String first2 = pinyin4j.toPinYinUppercase(str, "**");
//		String first3 = pinyin4j.toPinYinLowercase(str);
//		String first4 = pinyin4j.toPinYinLowercase(str,"**");
		String first5 = pinyin4j.toPinYinUppercaseInitials(str);
//		String first6 = pinyin4j.toPinYinLowercaseInitials(str);
//		System.out.println(first1);	//输出结果：YHY
//		System.out.println(first2);	//输出结果：Y**H**Y
//		System.out.println(first3);	//输出结果：yhy
//		System.out.println(first4);	//输出结果：y**h**y
		System.out.println(first5);	//输出结果：Y
//		System.out.println(first6);	//输出结果：y

	}
}
