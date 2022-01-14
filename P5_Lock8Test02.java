package org.example.juc;

import java.util.concurrent.TimeUnit;

public class P5_Lock8Test02 {
    /**
     * 多线程的8锁
     * 1、标准访问，请问先打印邮件还是短信？
     * 2、邮件方法暂停4秒钟，请问先打印邮件还是短信？
     */
    public static void main(String[] args) throws InterruptedException {
        Phone1 phone = new Phone1();
        new Thread(() -> {
            try {
                phone.call();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(200);

        new Thread(phone::sendMassage,"B").start();
    }
}

class Phone1 {
    public synchronized void sendMassage() {
        System.out.println("SendMessage!");
    }
    public synchronized void call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("call!");
    }
}
