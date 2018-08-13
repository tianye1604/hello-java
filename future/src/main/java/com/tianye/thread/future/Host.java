package com.tianye.thread.future;

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
    public Data request(final int count, final char c) {
        System.out.println("    request(" + count + "," + c + ") BEGIN");

        //1、创建FutureData的实例
        final FutureData future = new FutureData();

        //2、启动一个新线程，用于创建RealData的实例
        new Thread() {
            public void run() {
                RealData realdata = new RealData(count,c);
                future.setRealData(realdata);
            }
        }.start();

        System.out.println("    request(" + count + "," + c + ") END");

        //3、返回FutureData的实例
        return future;


    }
}
