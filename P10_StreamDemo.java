package org.example.juc;

import java.util.Arrays;
import java.util.List;

public class P10_StreamDemo {
    public static void main(String[] args) {
        /**
         * 常用：
         * 数据源：Collections集合
         * 中间操作：filter过滤  distinct 去重 map映射 sorted排序 limit截取
         * 终端操作：收集为Array 收集为Collection 收集为String Count计数 max min 最大最小值
         *
         * Consumer 消费型接口 有输入 无输出
         * Supplier 生产型接口 无输入 有输出
         * Function 函数型接口 有输入 有输出
         * predicate 断定型接口 有输入 输出Boolean
         */

        /**
         * 题目：请按照给出数据，找出同时满足以下条件的用户
         * * 也即以下条件：
         * * 1、全部满足偶数ID
         * * 2、年龄大于24
         * * 3、用户名转为大写
         * * 4、用户名字母倒排序
         * * 5、只输出一个用户名字 limit
         */
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

       list.stream()
            .filter(user -> user.getId() % 2 == 0 && user.getAge() > 24)
            .map(s -> s.getUserName().toUpperCase())
            .sorted()
            .limit(1)
            .forEach(System.out::println);
    }
}

class User {
    private int id;
    private String userName;
    private int age;

    public User(int id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() { return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", age=" + age + '}'; }
}
