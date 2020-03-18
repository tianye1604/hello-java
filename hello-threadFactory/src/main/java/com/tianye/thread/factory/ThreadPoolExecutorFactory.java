package com.tianye.thread.factory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2018/8/15 19:10
 * @Description: 线程池工厂类
 */
public class ThreadPoolExecutorFactory {

    /**
     * 执行过程是：
     * 1）当池子大小小于corePoolSize就新建线程，并处理请求
     * 2）当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去从workQueue中取任务并处理
     * 3）当workQueue放不下新入的任务时，新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize就用RejectedExecutionHandler来做拒绝处理
     * 4）另外，当池子的线程数大于corePoolSize的时候，多余的线程会等待keepAliveTime长的时间，如果无请求可处理就自行销毁
     */

    /**
     * corePoolSize：核心运行的poolSize，也就是当超过这个范围的时候，就需要将新的Thread放入到等待队列中了；
     * maximumPoolSize：一般你用不到，当大于了这个值就会将Thread由一个丢弃处理机制来处理，但是当你发生：newFixedThreadPool的时候，corePoolSize和maximumPoolSize
     * 是一样的，而corePoolSize是先执行的，所以他会先被放入等待队列，而不会执行到下面的丢弃处理中，看了后面的代码你就知道了。
     * workQueue：等待队列，当达到corePoolSize的时候，就向该等待队列放入线程信息（默认为一个LinkedBlockingQueue），运行中的队列属性为：workers，为一个HashSet；
     * keepAliveTime：默认都是0，当线程没有任务处理后，保持多长时间，cachedPoolSize是默认60s，不推荐使用。threadFactory：是构造Thread
     * 的方法，你可以自己去包装和传递，主要实现newThread方法即可；handler：也就是参数maximumPoolSize达到后丢弃处理的方法，java提供了5
     * 种丢弃处理的方法，当然你也可以自己弄，主要是要实现接口：RejectedExecutionHandler中的方法：public void rejectedExecution(Runnabler,
     * ThreadPoolExecutor e)java默认的是使用：AbortPolicy，他的作用是当出现这中情况的时候会抛出一个异常；其余的还包含：
     * 1、CallerRunsPolicy：如果发现线程池还在运行，就直接运行这个线程
     * 2、DiscardOldestPolicy：在线程池的等待队列中，将头取出一个抛弃，然后将当前线程放进去。
     * 3、DiscardPolicy：什么也不做
     * 4、AbortPolicy：java默认，抛出一个异常：RejectedExecutionException。
     */
    /**
     * corePoolSize 核心线程数
     */
    private static final int corePoolSize = 10;
    /**
     * maximumPoolSize 最大线程数
     */
    private static final int maximumPoolSize = 10;
    /**
     * keepAliveTime 当线程数大于核心数时，此为终止前多余的空闲线程等待新任务的最长时间，线程池维护线程所允许的空闲时间
     */
    private static final int keepAliveTime = 60;
    /**
     * 执行前用于保持任务的队列（缓冲队列）
     */
    private static final int capacity = 60000;

    /**
     * 线程池对象
     */
    private static ThreadPoolExecutor threadPoolExecutor = null;

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        if(null == threadPoolExecutor){
            ThreadPoolExecutor t;
            synchronized (ThreadPoolExecutor.class){
                t = threadPoolExecutor;
                if(null == t) {
                    synchronized (ThreadPoolExecutor.class) {
                        t = new ThreadPoolExecutor(
                                corePoolSize,
                                maximumPoolSize,
                                keepAliveTime,
                                TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>(capacity),
                                new ThreadPoolExecutor.CallerRunsPolicy()
                                );
                    }
                    threadPoolExecutor = t;
                }
            }
        }
        return threadPoolExecutor;
    }

}
