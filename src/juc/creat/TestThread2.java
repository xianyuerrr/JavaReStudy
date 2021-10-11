package juc.creat;

/**
 * @auther xianyue
 * @date 2021/10/4 - 星期一 - 17:26
 **/
// 实现 Runnable 接口，重写 run()，将类上转型为 Runnable 并传入 Thread里 : Thread(Runnable threadOb, String threadName)
public class TestThread2 implements Runnable {
    public static void main(String[] args) {
        TestThread2 testThread = new TestThread2();
        new Thread(testThread, "testThread 1").start();
        new Thread(testThread, "testThread 2").start();
    }

    @Override
    public void run() {
        for (int i=0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " --> " + "我在看代码 " + i);
        }
    }
}
