package com.study.anything;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianshujian
 * Create Date: 2020/10/30 19:16
 * Description: ${DESCRIPTION}
 */
public class TestSingleThreadExecutor2 {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		for (int i = 0; i <100 ; i++) {
			executorService.submit(new A(i+1));
			executorService.submit(new B(i+1));
			executorService.submit(new C(i+1));
		}
		executorService.shutdown();


	}

	static class A implements Runnable {
		private int count;

		public A(int count) {
			this.count = count;
		}

		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				System.out.print("A");
			}
		}
	}

	static class B implements Runnable {
		private int count;

		public B(int count) {
			this.count = count;
		}

		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				System.out.print("B");
			}

		}
	}

	static class C implements Runnable {
		private int count;

		public  C(int count) {
			this.count = count;
		}

		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				System.out.print("C");
			}
			System.out.println();
		}
	}
}
