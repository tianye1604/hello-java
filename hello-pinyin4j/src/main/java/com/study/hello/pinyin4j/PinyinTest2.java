package com.study.hello.pinyin4j;

import net.duguying.pinyin.Pinyin;
import net.duguying.pinyin.PinyinException;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/2 16:05
 * @Description:
 */
public class PinyinTest2 {

	public static void main(String[] args) throws PinyinException {


		//pinyin 1.0，支持智能多音字匹配。强烈建议使用这个包
		Pinyin pinyin = new Pinyin();
		//将拼音的首字母输出
		String s1 = pinyin.translateFirstChar("重庆市");
		System.out.println("1.pinyin 1.0 "+s1);
		//将拼音转换成拼音字数组
		String [] s2 = pinyin.translateInArray("重庆市");
		print(s2,"2.pinyin 1.0 ");
		//有声调str
		String s3 = pinyin.translate("长沙");
		System.out.println("3.pinyin 1.0 "+s3);
		//没有声调
		String s4 = pinyin.translateNoMark("长沙");
		System.out.println("4.pinyin 1.0 "+s4);
		//添加逗号隔开，且没有声调
		String s5 = pinyin.translateWithSepNoMark("长沙");
		System.out.println("5.pinyin 1.0 "+s5);
		//没有声调
		String s6 = pinyin.translateNoMark("女");
		System.out.println("6.pinyin 1.0 "+s6);


	}
	//输出数组方法
	private static void print(String[] strs,String pinyin){
		System.out.println(pinyin);
		for(String str:strs){
			System.out.println(str);
		}
	}


}
