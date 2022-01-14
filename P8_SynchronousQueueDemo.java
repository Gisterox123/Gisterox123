package org.example.juc;


import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


public class P8_SynchronousQueueDemo {
    public static void main(String[] args) {
        /**
         * SynchronousQueue没有容量，每一个put操作都需要一个take操作
         */
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        for (int i = 0; i < 5; i++) {
            final Integer integer =i;
            new Thread(()-> {
                try {
                    queue.put(integer);
                    System.out.println(Thread.currentThread().getName()+"put成功！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"put线程"+String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(()-> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(Thread.currentThread().getName()+"take成功："+queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"take线程"+String.valueOf(i)).start();
        }

    }
}
