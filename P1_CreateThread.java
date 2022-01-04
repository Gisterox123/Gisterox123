package org.example.juc;

public enum P2_EnumState {
    /**
     *线程状态
     */

    //刚刚创建
    NEW,
    //在JVM中运行
    RUNNABLE,
    //阻塞状态
    BLOCKED,
    //等待状态
    WAITING,
    //超时等待
    TIMED_WAITING,
    //执行结束，退出状态
    TERMINATED;

}
