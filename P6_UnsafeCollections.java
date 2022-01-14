package org.example.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class P6_UnsafeCollections {
    public static void main(String[] args) throws InterruptedException {
        //多线程下集合类不安全
        //List
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },"线程"+i).start();
        }
        System.out.println(list);
        System.out.println("=======================================");

        /**
         * 1 故障现象：ConcurrentModificationException
         * 2 导致原因：add 方法没有加锁
         * 3 解决方案：换一个集合类
         * 1、List<String> list = new Vector<>(); JDK1.0 就存在了！
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>
         ());
         * 3、List<String> list = new CopyOnWriteArrayList<>();
         */
        List<String> stringList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                stringList.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+":"+stringList);
            },String.valueOf(i)).start();
        }

        /**
         * set不安全
         * 1 故障现象：ConcurrentModificationException
         * 2 导致原因：add 方法没有加锁
         * 3 解决方案：换一个集合类
         * 1、Set<String> set = new HashSet<>(); 默认
         * 2、Set<String> set = Collections.synchronizedSet(new HashSet<>());
         * 3、Set<String> set = new CopyOnWriteArraySet();
         * 4 优化建议：（同样的错误，不出现第2次）
         *
         */

        // map不安全
        // Map<String,String> map = new HashMap<>();
        // // 等价于
        // Map<String,String> map = new HashMap<>(16,0.75);
        // 工作中，常常会自己根据业务来写参数，提高效率

        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }
}
