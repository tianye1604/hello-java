package com.study.anything;

import com.study.anything.model.UserInfo;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tianshujian
 * Create Date: 2020/1/6 17:46
 * Description: ${DESCRIPTION}
 */
public class ListSortTest {
//	public static void main(String[] args) {
//		Integer[] arr = new Integer[] {2,43,2,5,7,6,32,1,5,3,33};
//		List<Integer> list = Arrays.asList(arr);
//		list.sort(((o1, o2) -> o1.compareTo(o2)));
//		System.out.println(Arrays.toString(list.toArray()));
//
//
//
//
//	}


	public static void main(String[] args) {
		List<UserInfo>  carInfoDTOS = new ArrayList<>();

		UserInfo dto = new UserInfo();
		dto.setAge(10);
		dto.setGrade(0);
		UserInfo dto1 = new UserInfo();
		dto1.setAge(1);
		dto1.setGrade(1);
		UserInfo dto2 = new UserInfo();
		dto2.setAge(1);
		dto2.setGrade(2);

		UserInfo dto3 = new UserInfo();
//		dto3.setAge(1);
		dto3.setGrade(2);

		carInfoDTOS.add(dto);
		carInfoDTOS.add(dto1);
		carInfoDTOS.add(dto2);
		carInfoDTOS.add(dto3);

		List<UserInfo> userInfoList = carInfoDTOS.stream().sorted((o1,o2)->{
			if(Objects.nonNull(o1.getAge()) && Objects.nonNull(o2.getAge())) {
				return o1.getAge()*o1.getGrade() - o2.getAge()*o2.getGrade();
			}
			return o1.getGrade()-o2.getGrade();
		}).collect(Collectors.toList());

		System.out.println(userInfoList);

	}
}
