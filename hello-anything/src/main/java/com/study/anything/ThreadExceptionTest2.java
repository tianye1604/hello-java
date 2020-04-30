package com.study.anything;

import java.util.concurrent.*;

/**
 * Created by tianshujian
 * Create Date: 2020/1/19 18:05
 * Description: ${DESCRIPTION}
 */
public class ThreadExceptionTest2 {

	public static void main(String[] args) throws InterruptedException {

//		Executor ex  = Executors.newFixedThreadPool(10);
//
//		Executor ex  = Executors.newFixedThreadPool(10);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 300000, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(1000));
		// 设置线程池占满时的拒绝策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

		CountDownLatch latch = new CountDownLatch(10);

		System.out.println(Thread.currentThread().toString());
		for(int i= 0;i<10;i++) {
			executor.execute(() -> {
				try {
					String aa = "1";
					System.out.println(aa);
					latch.countDown();
					System.out.println("1 --->" + latch.getCount());

				} catch (Exception e) {
					System.out.println(" error  " + e);
				}

			});
		}
		latch.await();
		((ExecutorService) executor).shutdown();
		CountDownLatch latch2 = new CountDownLatch(10);

		for(int i= 0;i<10;i++) {
			executor.execute(() -> {
				try {
					String aa = "2";
					System.out.println(aa);
					latch2.countDown();
					System.out.println("2 --->" + latch2.getCount());
				} catch (Exception e) {
					System.out.println(" error  "+ e);
				}

			});
		}
		latch2.await();
		System.out.println("ee");


	}
}
