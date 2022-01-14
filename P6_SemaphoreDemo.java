package org.example.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class P6_SemaphoreDemo {
    public static void main(String[] args) {
        /**
         * acquire（获取）
         * 当一个线程调用 acquire 操作时，他要么通过成功获取信号量（信号量-1）
         * 要么一直等下去，直到有线程释放信号量，或超时
         * release （释放）
         * 实际上会将信号量的值 + 1，然后唤醒等待的线程。
         * （release）
         * 信号量主要用于两个目的：一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
         */
        //semaphore 模拟资源类 有三个空车位
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 15; i++) {//六辆车
            new Thread(()->{
                try {
                    semaphore.acquire();//acquire 得到
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(Thread.currentThread().getName()+"离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();// release 释放
                }
            },String.valueOf(i)).start();
        }
    }
}
