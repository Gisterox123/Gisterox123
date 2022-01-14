package org.example.juc;

import java.util.concurrent.TimeUnit;

public class P5_Lock8Test03 {
    /**
     * 多线程的8锁
     * 1、标准访问，请问先打印邮件还是短信？
     * 2、邮件方法暂停4秒钟，请问先打印邮件还是短信？
     * 3、新增一个普通方法hello()没有同步,请问先打印邮件还是hello？
     * 普通方法不受影响
     */
    public static void main(String[] args) throws InterruptedException {
        Phone3 phone = new Phone3();
        new Thread(() -> {
            try {
                phone.call();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(200);

        new Thread(phone::hello,"B").start();
    }
}

class Phone3 {
    public synchronized void sendMassage() {
        System.out.println("SendMessage!");
    }
    public synchronized void call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("call!");
    }
    public void hello(){
        System.out.println("Hello!");
    }
}
