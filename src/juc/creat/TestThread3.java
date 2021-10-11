package juc.creat;

import java.util.concurrent.*;

/**
 * @auther xianyue
 * @date 2021/10/4 - 星期一 - 19:25
 **/
// 方式三 : 实现 Callable 接口，通过提交给线程池来执行，可以有返回值、可以抛出异常
public class TestThread3 implements Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThread3 t1 = new TestThread3();

        // 创建线程池
        ExecutorService ser = Executors.newFixedThreadPool(2);

        // 提交执行
        Future<String> r1 = ser.submit(t1);
        Future<String> r2 = ser.submit(t1);

        // 获取结果
        String res1 = r1.get();
        String res2 = r2.get();

        System.out.println("******************");
        System.out.println(res1);
        System.out.println(res2);
        // 关闭线程池
        ser.shutdownNow();
    }

    @Override
    public String call() throws Exception {
        for (int i=0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " --> 走到了 " + i + " 位置");
        }
        return Thread.currentThread().getName();
    }
}
