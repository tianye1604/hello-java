package com.study.anything;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by tianshujian
 * Create Date: 2020/1/19 18:05
 * Description: ${DESCRIPTION}
 */
public class ThreadExceptionTest {

	public static void main(String[] args) {

		Executor ex  = Executors.newFixedThreadPool(10);

		for(int i= 0;i<10;i++) {
			ex.execute(() -> {
				try {
					String aa = null;
					System.out.println(aa.length());
				} catch (Exception e) {
					System.out.println(" error  ");
				}

			});
		}

	}
}
