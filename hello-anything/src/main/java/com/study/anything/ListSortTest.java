package com.study.anything;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tianshujian
 * Create Date: 2020/1/6 17:46
 * Description: ${DESCRIPTION}
 */
public class ListSortTest {
	public static void main(String[] args) {
		Integer[] arr = new Integer[] {2,43,2,5,7,6,32,1,5,3,33};
		List<Integer> list = Arrays.asList(arr);
		list.sort(((o1, o2) -> o1.compareTo(o2)));
		System.out.println(Arrays.toString(list.toArray()));
	}
}
