package org.example.juc;

import java.util.concurrent.TimeUnit;

public class P5_Lock8Test04 {
    /**
     * 多线程的8锁
     * 1、标准访问，请问先打印邮件还是短信？
     * 2、邮件方法暂停4秒钟，请问先打印邮件还是短信？
     * 3、新增一个普通方法hello()没有同步,请问先打印邮件还是hello？
     * 4、两部手机、请问先打印邮件还是短信？
     * synchronized锁的对象是对象，两个对象两个锁不会相互影响
     */
    public static void main(String[] args) throws InterruptedException {
        Phone4 phone = new Phone4();
        Phone4 phone4 = new Phone4();
        new Thread(() -> {
            try {
                phone.call();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(200);

        new Thread(phone4::sendMassage,"B").start();
    }
}

class Phone4 {
    public synchronized void sendMassage() {
        System.out.println("SendMessage!");
    }
    public synchronized void call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("call!");
    }
}
