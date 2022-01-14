package org.example.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class P8_BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue 都给你一手包办了
         * ArrayBlockingQueue：由数组结构组成的有界阻塞队列。
         * LinkedBlockingQueue：由链表结构组成的有界（默认值为：integer.MAX_VALUE）阻塞队列。
         * SynchronousQueue：不存储元素的阻塞队列，也即单个元素的队列。
         */
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        // add 添加
        System.out.println(blockingQueue.add("a")); //true
        System.out.println(blockingQueue.add("b")); //true
        System.out.println(blockingQueue.add("c")); //true
        // System.out.println(blockingQueue.add("d"));//IllegalStateException  Queue full

        System.out.println(blockingQueue.element()); // 检测队首元素 a

        // public E remove() 返回值E，就是移除的值
        System.out.println(blockingQueue.remove()); //a
        System.out.println(blockingQueue.remove()); //b
        System.out.println(blockingQueue.remove()); //c
        // System.out.println(blockingQueue.remove()); // java.util.NoSuchElementException

        System.out.println("--------------------------------------------------------");

        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(3);
        // 返回特殊值
        System.out.println(blockingQueue1.offer("a")); // true
        System.out.println(blockingQueue1.offer("b")); // true
        System.out.println(blockingQueue1.offer("c")); // true
        System.out.println(blockingQueue1.offer("d")); // false

        System.out.println(blockingQueue1.peek()); // 检测队首位置 a

        // public E poll()
        System.out.println(blockingQueue1.poll()); // a
        System.out.println(blockingQueue1.poll()); // b
        System.out.println(blockingQueue1.poll()); // c
        System.out.println(blockingQueue1.poll()); // null

        System.out.println("-------------------------一直阻塞-------------------------------");
        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);
        blockingQueue2.put("a");
        blockingQueue2.put("b");
        blockingQueue2.put("c");
        // blockingQueue2.put("d");//一直阻塞

        System.out.println(blockingQueue2.take()); //a
        System.out.println(blockingQueue2.take()); //b
        System.out.println(blockingQueue2.take()); //c
        // System.out.println(blockingQueue2.take()); //阻塞等待

        System.out.println("-------------------超时退出------------------------");
        ArrayBlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<>(3);
        blockingQueue3.offer("a");
        blockingQueue3.offer("b");
        blockingQueue3.offer("c");
        blockingQueue3.offer("d",3L, TimeUnit.SECONDS);

        System.out.println(blockingQueue3.poll()); //a
        System.out.println(blockingQueue3.poll()); //b
        System.out.println(blockingQueue3.poll()); //c
        System.out.println(blockingQueue3.poll(3L,TimeUnit.SECONDS)); //超时不等待

    }
}
