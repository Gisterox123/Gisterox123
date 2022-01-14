package org.example.juc;

import java.util.concurrent.CountDownLatch;

public class P6_CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * CountDownLatch 减法计数器
         * CountDownLatch 主要有两个方法，当一个或多个线程调用 await 方法时，这些线程会阻塞
         * 其他线程调用CountDown方法会将计数器减1（调用CountDown方法的线程不会阻塞）
         * 当计数器变为0时，await 方法阻塞的线程会被唤醒，继续执行
         */
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "Start");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"End");
    }
}
