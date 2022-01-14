package org.example.juc;

import java.util.concurrent.TimeUnit;

public class P5_Lock8Test07 {
    /**
     * 多线程的8锁
     * 1、标准访问，请问先打印邮件还是短信？
     * 2、邮件方法暂停4秒钟，请问先打印邮件还是短信？
     * 3、新增一个普通方法hello()没有同步,请问先打印邮件还是hello？
     * 4、两部手机、请问先打印邮件还是短信？
     * 5、两个静态同步方法，同一部手机，请问先打印邮件还是短信？
     * 6、两个静态同步方法，2部手机，请问先打印邮件还是短信？
     * 7、一个普通同步方法，一个静态同步方法，同一部手机，请问先打印邮件还是短信？
     * 8、一个普通同步方法，一个静态同步方法，2部手机，请问先打印邮件还是短信？
     * 静态类锁在Class对象，普通同步方法锁在方法调用对象，不影响相互功能
     *
     * 对于普通同步方法，锁的是当前实例对象
     * 对于静态同步方法，锁的是当前的Class对象。
     * 对于同步方法块，锁是synchronized括号里面的配置对象
     */
    public static void main(String[] args) throws InterruptedException {
        Phone7 phone7 = new Phone7();
        new Thread(()->{
            try {
                Phone7.call();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(200);
        new Thread(phone7::sendMassage,"B").start();
    }
}

class Phone7 {
    public synchronized void sendMassage() {
        System.out.println("SendMessage!");
    }
    public static synchronized void call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("call!");
    }
}
