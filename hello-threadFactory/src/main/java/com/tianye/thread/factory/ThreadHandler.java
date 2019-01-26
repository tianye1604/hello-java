package com.tianye.thread.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/15 19:50
 * @Description:
 */
public class ThreadHandler<T> extends ThreadHandlerAbstract {
    private static final Logger logger = LoggerFactory.getLogger(ThreadHandler.class);

    protected T t;
    protected Class<T> modelClass;
    protected String method = "";

    public ThreadHandler(Integer threadCount,T t) {
        this.t = t;
        modelClass = (Class<T>) t.getClass();
        if(null != threadCount) {
            super.countDownLatch = new CountDownLatch(threadCount);
        }
    }


    public void execute(IThreadPoolExecutorHandler threadPoolExecutorHandler, String method) throws Exception {
        this.method = method;

        try{
            ThreadPoolExecutor threadPoolExcecutor = ThreadPoolExecutorFactory.getThreadPoolExecutor();
            threadPoolExcecutor.execute(threadPoolExecutorHandler);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("线程池处理异常 execute 方法：{}",this.method,e);
            throw new Exception(e.getMessage(),e);
        }
    }

    public void await() throws Exception {
        try{
            if(super.countDownLatch != null) {
                countDownLatch.await();
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("线程池处理异常 await 方法：{}",this.method,e);
            throw new Exception(e.getMessage(),e);
        }
    }

    public void shutdown() throws Exception {
        try {
            ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorFactory.getThreadPoolExecutor();
            threadPoolExecutor.shutdown();
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("线程池处理异常 shutdown 方法：{}",this.method,e);
            throw new Exception(e.getMessage(),e);
        }
    }


    public void run() {
        try{
            Method[] methods = this.modelClass.getMethods();
            for (Method method : methods){
                if(method.getName().equals(this.method)){
                    method.invoke(t);
                }
            }
            if(null != super.countDownLatch){
                super.countDownLatch.countDown();
            }
            if(null != ThreadPoolExecutorFactory.getThreadPoolExecutor().getQueue()
                    && (ThreadPoolExecutorFactory.getThreadPoolExecutor().getQueue().size() < 20
                        || ThreadPoolExecutorFactory.getThreadPoolExecutor().getQueue().size() == 0) ){
                ThreadPoolExecutorFactory.getThreadPoolExecutor().setCorePoolSize(20);
            }else {
                ThreadPoolExecutorFactory.getThreadPoolExecutor().setCorePoolSize(40);
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.error("线程池处理异常 方法：{}",this.method,e );
        }
    }

    /**
     * 获取线程池队列状态数量
     * @return
     */
    public int getQueueSize() {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorFactory.getThreadPoolExecutor();
        return threadPoolExecutor.getQueue().size();
    }

    public int getPoolSize() {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorFactory.getThreadPoolExecutor();
        return threadPoolExecutor.getPoolSize();
    }


}
