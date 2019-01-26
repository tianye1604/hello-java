package com.tianye.thread.factory;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/15 19:37
 * @Description:
 */
public abstract class ThreadHandlerAbstract implements IThreadPoolExecutorHandler {

    /**
     * 一个任务分多个线程处理时使用
     */
    protected CountDownLatch countDownLatch = null;

    public ThreadHandlerAbstract(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public ThreadHandlerAbstract() {}

    public abstract void execute(IThreadPoolExecutorHandler threadPoolExecutorHandler,String method) throws Exception;

    public abstract void await() throws Exception;

    public abstract void shutdown() throws Exception;
}
