package com.study.anything;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianshujian
 * Create Date: 2020/10/30 19:16
 * Description: ${DESCRIPTION}
 */
public class TestSingleThreadExecutor3 {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		int total = 20;
		for (int i = 0; i <total ; i++) {
			int num = (total - (2*i+1))/2;
			if(num <= 0) {
				break;
			}
			executorService.submit(new PrefixBlank(num));
			executorService.submit(new A(2*i+1));
//			executorService.submit(new B(i+1));
//			executorService.submit(new C(i+1));
			executorService.submit(new SuffixBlank(num));
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

		}
	}

	static class PrefixBlank implements Runnable {
		private int count;

		public PrefixBlank(int count) {
			this.count = count;
		}

		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				System.out.print(" ");
			}
		}
	}

	static class SuffixBlank implements Runnable {
		private int count;

		public SuffixBlank(int count) {
			this.count = count;
		}

		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				System.out.print(" ");
			}
			System.out.println("");
		}


	}
}
