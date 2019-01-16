package com.tianye.thread.concurrent.future;

import java.util.concurrent.Callable;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/13 10:59
 * @Description:
 */
public class Host {

    /**
     * 1.创建FutureData
     * 2.启动一个新线程，用于创建RealData的实例
     * 3.将FutureData的实例作为返回值返回给调用者
     * @param count
     * @param c
     * @return
     *
     * 小知识：
     * 1、因为request 中使用的参数（count,c)和局部变量（future)都是调用了request方法的那些线程各自所特有的，
     * 它们并不会在多线程之间共享，所以虽然request方法并非synchronized方法，也是线程安全的；
     * 2、request方法的参数（count、c)和局部变量（future)都是final的，这是因为匿名内部类使用了它们；
     */
    public FutureData request(final int count, final char c) {
        System.out.println("    request(" + count + "," + c + ") BEGIN");

        //1、创建FutureData的实例
        FutureData future = new FutureData(
                new Callable<RealData>() {

                    public RealData call() throws Exception {
                        return new RealData(count,c);
                    }
                }
        );

        //2、启动一个新线程，用于创建RealData的实例
       new Thread(future).start();

        System.out.println("    request(" + count + "," + c + ") END");

        //3、返回FutureData的实例
        return future;


    }
}
