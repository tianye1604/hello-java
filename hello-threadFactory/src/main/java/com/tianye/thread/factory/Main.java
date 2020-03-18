package com.tianye.thread.factory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/15 20:17
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws Exception {
//        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorFactory.getThreadPoolExecutor();
//        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
//        ExecutorService threadPoolExecutor = Executors.newWorkStealingPool(10);
        Integer poolSize = 1000;
        Integer taskNum = 1000;
        Integer countdownLatchNum  = taskNum/poolSize +1;
        CountDownLatch countDownLatch2 = new CountDownLatch(taskNum);

            for (int i = 0; i < taskNum ; i++) {

                threadPoolExecutor.execute(() -> {
                    System.out.println("线程ID : " + Thread.currentThread().getId() );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch2.countDown();
                });
                System.out.println("===================================================================");
            }
        countDownLatch2.await();
        System.out.println("结束");
//        Count count = new Count();
//        ThreadHandler<Count> threadHandler = new ThreadHandler<Count>(20,count);
//        for(int i=0; i< 10000; i++) {
//            final int index = i;
//            threadHandler.execute(threadHandler,"add");
//        }
    }
}
