package org.example.juc;

import java.util.concurrent.Callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class P1_CreateThread {
    public static void main(String[] args) {
        /**
         * 第一种创建线程的方式 写一个类继承Thread类，重写run方法
         * new 这个子对象并调用start方法启动线程
         */
        new TicketThread().start();
        /**
         * 第二种方式实现Runnable接口,并实现run方法
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("dad");
            }
        }, "A").start();

        /**
         * 第三种方式实现Callable接口，并结合Future实现
         *+
         */
        FutureTask<Integer> target = new FutureTask<>(new CallableThread());
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "的循环变量i的值" + i);
            if (i == 15) {
                new Thread(target, "有返回值的线程").start();
            }
        }

        try {
            System.out.println("有返回值的线程返回值：" + target.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        /**
         * 拆解一下代码就会知道，这是一个继承了 Thread 父类的子类对象，重写了父类的 run 方法。然后，父对象 Thread 中，在构造方法中传入了一个 Runnable 接口的实现类，实现了 run 方法。
         * 现在执行了 start 方法，必然会先在子类中寻找 run 方法，找到了就会直接执行，不会执行父类的 run 方法了，因此结果为：Thread run 。
         * 若假设子类没有实现 run 方法，那么就会去父类中寻找 run 方法，而父类的 run 方法会判断是否有 Runnable传过来（即判断target是否为空），现在 target 不为空，因此就会执行 target.run 方法，即打印结果： runnable。
         * 所以，上边的代码看起来复杂，实则很简单。透过现象看本质，我们就会发现，它不过就是考察类的父子继承关系，子类重写了父类的方法就会优先执行子类重写的方法。
         */
        new Thread(() -> System.out.println("runnable"), "A") {
            @Override
            public void run() {
                System.out.println("Thread run");
            }
        }.start();
    }
}

class TicketThread extends Thread {
    @Override
    public void run() {
        System.out.println("sada");
    }
}

class CallableThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }
}
