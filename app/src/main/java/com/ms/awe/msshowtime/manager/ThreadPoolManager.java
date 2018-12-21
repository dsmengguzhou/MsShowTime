package com.ms.awe.msshowtime.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created By MusiBro
 * on 2018/12/21
 * 线程池管理，整个项目中所有的线程，所以不能有多个实例对象，要用单例设计
 */
public class ThreadPoolManager {
    /**
     * 单例，饿汉式
     */
    private static ThreadPoolManager mInstance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return mInstance;
    }

    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime = 1;
    private TimeUnit unit = TimeUnit.HOURS;
    private ThreadPoolExecutor executor;

    private ThreadPoolManager() {
        //给corePoolSize赋值：当前设备可用处理器核心数 * 2 + 1,能够让CPU的效率得到最大程度的执行
        corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        maximumPoolSize = corePoolSize;     //虽然maximumPoolSize用不到，但是需要赋值，否则报错
        executor = new ThreadPoolExecutor(
                corePoolSize,       //当某个核心任务执行完毕，会一次从缓冲队列中取出等待任务
                maximumPoolSize,    //corePoolSize_new LinkedBlockingQueue<Runnable>()_maximumPoolSize,包含corePoolSize
                keepAliveTime,      //表示的是maximumPoolSize当中等待任务的存货事件
                unit,
                new LinkedBlockingQueue<Runnable>(),    //缓冲队列，用于存放等待任务，Linked先进先出
                Executors.defaultThreadFactory(),       //创建线程的工厂
                new ThreadPoolExecutor.AbortPolicy()    //用来对超出maximumPoolSize的任务的处理策略
        );
    }

    /**
     * 执行任务
     */
    public void execute(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        executor.execute(runnable);
    }

    /**
     * 从线程中移除任务
     */
    public void remove(Runnable runnable){
        if (runnable == null){
            return;
        }
        executor.remove(runnable);
    }
}
