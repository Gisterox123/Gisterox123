package org.example.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class P4_ProductConsumer4 {
    public static void main(String[] args) {
        /**
         * 题目：多线程之间按顺序调用，实现 A->B->C
         * 三个线程启动，要求如下：
         * AA 打印5次，BB 打印10次。CC打印15次，依次循环
         *
         * 重点：标志位
         */
        Data4 data = new Data4();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    data.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    data.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    data.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}

class Data4{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public  void print5() throws InterruptedException {
        lock.lock();
        try{
            //线程条件
            while(number!=1)
                condition1.await();
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+"\t"+i);
            }
            //通知唤醒
            number=2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void print10() throws InterruptedException {
        lock.lock();
        try{
            while (number!=2)
                condition2.await();
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+"\t"+i);
            }
            number=3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void print15() throws InterruptedException {
        lock.lock();
        try{
            while (number!=3)
                condition3.await();
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+"\t"+i);
            }
            number=1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
