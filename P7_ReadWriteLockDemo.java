package org.example.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class P7_ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
         * * 但是，如果有一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读或写。
         * * 1. 读-读 可以共存
         * * 2. 读-写 不能共存
         * * 3. 写-写 不能共存
         */
        // MyCache myCache = new MyCache();
        // for (int i = 0; i < 10; i++) {
        //     final Integer tempInt = i;
        //     new Thread(() -> {
        //         myCache.put(tempInt, UUID.randomUUID().toString().substring(0, 8));
        //     }, "写入线程"+String.valueOf(i)).start();
        //     new Thread(() -> {
        //         myCache.get(tempInt);
        //     }, "读取线程"+String.valueOf(i)).start();
        // }
        // for (int i = 0; i < 10; i++) {
        //     final Integer tempInt = i;
        //     new Thread(() -> {
        //         myCache.get(tempInt);
        //     }, "读取线程"+String.valueOf(i)).start();
        // }
        // TimeUnit.SECONDS.sleep(5);
        // System.out.println("======================");

        MyCacheLock myCacheLock = new MyCacheLock();
        for (int i = 0; i < 10; i++) {
            final Integer temp = i;
            new Thread(() -> {
                myCacheLock.put(temp, UUID.randomUUID().toString().substring(0, 8));
                new Thread(()->{myCacheLock.get(temp);},"LOCK读取线程"+String.valueOf(temp)).start();
            }, "LOCK写入线程"+String.valueOf(i)).start();
        }
        // for (int i = 0; i < 10; i++) {
        //     final Integer temp = i;
        //     new Thread(() -> {
        //         myCacheLock.get(temp);
        //     }, "读取线程"+String.valueOf(i)).start();
        // }
    }
}

class MyCache {
    private volatile Map<Integer, Object> map = new HashMap<>();

    public void put(Integer k, Object v) {
        map.put(k, v);
        System.out.println(Thread.currentThread().getName() + "写入成功！");
    }

    public void get(Integer k) {
        System.out.println(Thread.currentThread().getName() + "读取成功！"+map.get(k));
    }
}

class MyCacheLock {
    private volatile Map<Integer, Object> map = new HashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(Integer k, Object v) {
        lock.writeLock().lock();
        try {
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "写入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(Integer k) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取成功！"+map.get(k));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}
