package org.example.juc;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class P9_ThreadPoolDemo {
    public static void main(String[] args) {
        /**
         * 线程池主要特点为：线程复用，控制最大并发数，管理线程
         *
         *Java中的线程池是通过 Executor 框架实现的，该框架中用到了 Executor ，Executors，
         * ExecutorService，ThreadPoolExecutor 这几个类。
         *
         * 底层都是调用ThreadPoolExecutor
         * 线程池的七大参数
         * 核心线程数，最大线程数，空闲线程存活时间，存活时间单位，线程队列（ArrayBlockingQueue,LinkedBlockingQueue,SynchronousQueue）
         * 线程工程（默认即可），拒绝策略（任务量大于最大线程数量。1.拒绝 2拒绝抛异常 3.抛弃阻塞队列前面的，尝试执行新任务 4由调用线程处理）
         */

        //执行很多短期异步任务
        // ExecutorService executorService = Executors.newCachedThreadPool();//缓存池 自动伸缩扩容
        // try {
        //     for (int j = 0; j <1000; j++) {
        //         final Integer integer = j;
        //         executorService.execute(() -> {
        //             System.out.println(Thread.currentThread().getName() + "办理业务！" + integer);
        //             try {
        //                 TimeUnit.SECONDS.sleep(3);
        //             } catch (InterruptedException e) {
        //                 e.printStackTrace();
        //             }
        //         });
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }finally {
        //     executorService.shutdown();
        // }

        // ExecutorService executorService1 = Executors.newSingleThreadExecutor(); // 单线程池
        // try {
        //     for (int j = 0; j <200; j++) {
        //         final Integer integer=j;
        //         executorService1.execute(() -> {
        //             System.out.println(Thread.currentThread().getName() + "办理业务！"+integer);
        //             try {
        //                 TimeUnit.SECONDS.sleep(3);
        //             } catch (InterruptedException e) {
        //                 e.printStackTrace();
        //             }
        //         });
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }finally {
        //     executorService1.shutdown();
        // }

        // ExecutorService executorService = Executors.newFixedThreadPool(5); //指定线程数量
        // try {
        //     for (int j = 0; j <200; j++) {
        //         final Integer integer=j;
        //         executorService.execute(() -> {
        //             try {
        //                 TimeUnit.SECONDS.sleep(3);
        //             } catch (InterruptedException e) {
        //                 e.printStackTrace();
        //             }
        //             System.out.println(Thread.currentThread().getName() + "办理业务！"+integer);
        //         });
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }finally {
        //     executorService.shutdown();
        // }

        //拒绝策略
        // RejectedExecutionHandler rejected = null;
        // rejected = new ThreadPoolExecutor.AbortPolicy();//默认，队列满了丢任务，抛出异常
        // rejected = new ThreadPoolExecutor.DiscardPolicy();//队列满了丢任务，不抛出异常【如果允许任务丢失这是最好的】
        // rejected = new ThreadPoolExecutor.DiscardOldestPolicy();//将最早进入队列的任务删，之后再尝试加入队列
        //     rejected = new ThreadPoolExecutor.CallerRunsPolicy();//如果添加到线程池失败，那么主线程会自己去执行该任务，回退

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 22, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(20), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int j = 0; j <200; j++) {
                final Integer integer=j;
                threadPoolExecutor.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "办理业务！"+integer);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }

    }
}
