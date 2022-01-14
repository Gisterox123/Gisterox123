package org.example.juc;

public class P5_Lock8Test01 {
    /**
     * synchronized锁的对象是方法的调用者，同一个对象先调用先执行
     */
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(phone::call,"AA").start();
        new Thread(phone::sendMassage,"BB").start();
    }
}

class Phone {
    public synchronized void sendMassage() {
        System.out.println("SendMessage!");
    }
    public synchronized void call() {
        System.out.println("call!");
    }
}
