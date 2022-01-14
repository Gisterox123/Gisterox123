package org.example.juc;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class P11_ForkJoinWorkDemo {
    /**
     * 这个框架针对的是大任务执行，效率才会明显的看出来有提升，于是我把总数调大到20亿
     * 核心类：ForkJoinPool
     * ForkJoinTask运行在ForkJoinPool中的任务
     * ForkJoinTask的主要方法：
     * fork()在池中创建一个子任务
     * join()当任务完成时返回结果
     * invoke()开始执行任务，如果必要等待完成
     *
     * 子类：Recuesive
     * RecursiveTask() 有返回值
     * RecursiveAction()无返回值
     *
     * 打个比方，假设一个酒店有400个房间，一共有4名清洁工，每个工人每天可以打扫100个房间，这样，4
     * 个工人满负荷工作时，400个房间全部打扫完正好需要1天。
     * Fork/Join的工作模式就像这样：首先，工人甲被分配了400个房间的任务，他一看任务太多了自己一个
     * 人不行，所以先把400个房间拆成两个200，然后叫来乙，把其中一个200分给乙。
     * 紧接着，甲和乙再发现200也是个大任务，于是甲继续把200分成两个100，并把其中一个100分给丙，
     * 类似的，乙会把其中一个100分给丁，这样，最终4个人每人分到100个房间，并发执行正好是1天。
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testForkJoin();
        test();
        testStream();

    }
    public static void testForkJoin() throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinWork forkJoinWork = new ForkJoinWork(0L, 200000000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinWork);
        Long aLong = submit.get();
        System.out.println("forkJoin_Time"+(System.currentTimeMillis()-l)+"结果"+aLong);
    }
    public static void test() throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        Long sum =0L;
        for (long i = 0L; i < 200000000L; i++) {
            sum+=i;
        }
        System.out.println("fori"+(System.currentTimeMillis()-l)+"结果"+sum);
    }
    public static void testStream() throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        long reduce1 = LongStream.rangeClosed(0L, 200000000L).parallel().reduce(0, Long::sum);
        System.out.println("Stream"+(System.currentTimeMillis()-l)+"结果"+reduce1);
    }

}
class ForkJoinWork extends RecursiveTask<Long>{
    private Long start;
    private Long end;
    public static final Long critical = 10000L;

    public ForkJoinWork(Long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        //判断是否拆分完毕
        Long lenth = end-start;
        if(lenth<=critical) {
            //拆分完毕就想加
            Long sum = 0L;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }else{
            Long middle = (start+end)/2;
            ForkJoinWork left = new ForkJoinWork(start, middle);
            left.fork();
            ForkJoinWork right = new ForkJoinWork(middle+1, end);
            right.fork();
            return left.join()+right.join();
        }
    }
}

