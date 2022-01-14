package org.example.juc;

public class P4_ProductConsumer1{
    public static void main(String[] args) {
        /**
         * 题目：现在2个线程，可以操作初始值为0的一个变量
         * * 实现一个线程对该变量 + 1，一个线程对该变量 -1
         * * 实现交替10次
         */
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}

class Data{
    private int number = 0;
    public synchronized void increment() throws InterruptedException {
        while(number!=0)
            this.wait();
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number==0)
            this.wait();
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }
}
