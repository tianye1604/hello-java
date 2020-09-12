package com.tianye.hello.util;

import com.tianye.hello.util.enums.BankUserRoleEnum;

import java.util.Arrays;

/**
 * Created by tianshujian
 * Create Date: 2020/9/12 21:07
 * Description: ${DESCRIPTION}
 */
public class Main {
	public static void main(String[] args) {
		String[] names = EnumUtil.getNames(BankUserRoleEnum.COLLABORATOR.getClass());
		System.out.println(Arrays.toString(names));
	}
}
