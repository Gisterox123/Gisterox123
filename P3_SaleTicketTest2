package org.example.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class P3_SaleTicketTest2 {
    /**
     * synchronized 和 lock 区别
     * 1.synchronized是java关键字，lock是个java类
     * 2.synchronize会自动释放锁，而lock需要手动释放锁
     * 3.synchronize无法判断获取到锁的状态，lock可以判断是否获取到锁
     * 4.用synchronize的两个线程，如果线程1获得锁，线程2等待。如果线程1阻塞，则线程二会一直等待下去。而lock锁就不一定会。
     * 5.synchronize的锁可重入，不可中断，非公平。而lock锁可重入，可判断，可公平（两者皆可）
     * 6.lock适合大量代码的同步问题，synchronized锁适合少量代码的同步问题
     */

    public static void main(String[] args) {
        Ticket1 ticket = new Ticket1();
        new Thread(()->{ for (int i = 0; i <90; i++)ticket.saleTicket(); },"A").start();
        new Thread(()->{ for (int i = 0; i <90; i++)ticket.saleTicket(); },"B").start();
        new Thread(()->{ for (int i = 0; i <90; i++)ticket.saleTicket(); },"C").start();
    }
}

class Ticket1 { // 资源类
    private Lock lock =new ReentrantLock();

    private int number = 90;

    public  void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第 " + (number--) + "张票,还剩下:" + number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

