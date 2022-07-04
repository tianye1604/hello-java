package com.study.anything;

import com.jd.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tianshujian
 * Create Date: 2019/12/11 15:20
 * Description: ${DESCRIPTION}
 */
public class SetTest {

	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		set.add("aaa");
		set.add("bbb");
		set.add("ccc");
		set.add("ddd");
		System.out.println(JSON.toJSON(set));

		//1. list构造方法
		List<String> list = new ArrayList<>(set);

		//2. stream
		List<String> list2 = set.stream().collect(Collectors.toList());

		System.out.println(list.toString());
		System.out.println(list2.toString());
	}
}
