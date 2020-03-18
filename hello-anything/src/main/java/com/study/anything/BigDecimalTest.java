package com.study.anything;

import java.math.BigDecimal;

/**
 * Created by tianshujian
 * Create Date: 2020/1/15 14:56
 * Description: ${DESCRIPTION}
 */
public class BigDecimalTest {

	public static void main(String[] args) {
		BigDecimal canStealGold = new BigDecimal(100.911);
		BigDecimal reduceValue = new BigDecimal(0).subtract(canStealGold);
		System.out.printf(String.valueOf(reduceValue.longValue()));
	}
}
