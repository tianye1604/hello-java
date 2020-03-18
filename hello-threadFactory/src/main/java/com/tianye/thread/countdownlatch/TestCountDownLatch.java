package com.tianye.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tianshujian
 * Create Date: 2020/3/18 15:00
 * Description: ${DESCRIPTION}
 */
public class TestCountDownLatch {

//	private CountDownLatch countDownLatch;
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(10);

		for (int i= 0;i<1000;i++) {
			new Thread(() ->{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " is done");
			}, "thread"+ i).start();
			countDownLatch.countDown();
		}
		countDownLatch.await();
		System.out.println("==========end==========");

	}
}
