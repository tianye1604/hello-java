package com.tianye.thread.factory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/15 20:17
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorFactory.getThreadPoolExecutor();
        Count count = new Count();
        ThreadHandler<Count> threadHandler = new ThreadHandler<Count>(20,count);
        for(int i=0; i< 10000; i++) {
            final int index = i;
            threadHandler.execute(threadHandler,"add");
        }
    }
}
