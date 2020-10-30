package com.study.anything;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianshujian
 * Create Date: 2020/10/30 19:16
 * Description: ${DESCRIPTION}
 */
public class TestSingleThreadExecutor {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		for (int i = 0; i <100000 ; i++) {
			executorService.submit(new A());
			executorService.submit(new B());
			executorService.submit(new C());
		}


		executorService.shutdown();


	}

	static class A implements Runnable {
		@Override
		public void run() {
			System.out.print("A");
		}
	}

	static class B implements Runnable {
		@Override
		public void run() {
			System.out.print("B");
		}
	}

	static class C implements Runnable {
		@Override
		public void run() {
			System.out.println("C");
		}
	}
}
