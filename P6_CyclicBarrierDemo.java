package org.example.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class P6_CyclicBarrierDemo {
    public static void main(String[] args) {
        // CyclicBarrier(int parties, Runnable barrierAction) 加法计数器
        CyclicBarrier success = new CyclicBarrier(7, () -> {
            System.out.println("SUCCESS");
        });
        for (int i = 0; i < 7; i++) {
            int Int = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "获得了第" + Int + "颗龙珠");
                try {
                    success.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"悟空"+Int+"号").start();
        }
    }
}
