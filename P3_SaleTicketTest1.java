package org.example.juc;

public class P3_SaleTicketTest1 {
    /**
     * wait和sleep的区别，
     * 1.wait来自Object类，sleep来自thread类，一个静态方法，那个线程调用那个进入blocked状态
     * 2.sleep没有释放锁，相当于定时闹钟。wait释放了锁，无限期的，除非用户主动notify,之后还需要等待os调度
     * 3.wait方法在哪里都可以使用，而sleep，notify只能在同步代码块中使用
     * 4.wait方法必须捕获异常，sleep不需要
     */

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 30; i++) {
                    ticket.saleTicket();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.saleTicket();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.saleTicket();
                }
            }
        }, "C").start();
    }
}
    class Ticket { // 资源类
        private int number = 90;

        public synchronized void saleTicket() {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第 " + (number--) + "张票,还剩下:" + number);
            }
        }
    }
