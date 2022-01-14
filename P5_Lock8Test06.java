package org.example.juc;

import java.util.concurrent.TimeUnit;

public class P5_Lock8Test06 {
    /**
     * 多线程的8锁
     * 1、标准访问，请问先打印邮件还是短信？
     * 2、邮件方法暂停4秒钟，请问先打印邮件还是短信？
     * 3、新增一个普通方法hello()没有同步,请问先打印邮件还是hello？
     * 4、两部手机、请问先打印邮件还是短信？
     * 5、两个静态同步方法，同一部手机，请问先打印邮件还是短信？
     * 6、两个静态同步方法，2部手机，请问先打印邮件还是短信？
     * 7、一个普通同步方法，一个静态同步方法，同一部手机，请问先打印邮件还是短信？
     *
     * 被synchronized和static修饰的方法，锁的对象是类的class对象。仅仅被synchronized修饰的方
     * 法，锁的对象是方法的调用者。因为两个方法锁的对象不是同一个，所以两个方法用的不是同一个锁，
     * 后调用的方法不需要等待先调用的方法。
     */
    public static void main(String[] args) throws InterruptedException {
        Phone6 phone6 = new Phone6();
        new Thread(()->{
            try {
                phone6.call();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(200);
        new Thread(Phone6::sendMassage,"B").start();
    }
}

class Phone6 {
    public static synchronized void sendMassage() {
        System.out.println("SendMessage!");
    }
    public synchronized void call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("call!");
    }
}
