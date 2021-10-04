package juc.creatThread;

/**
 * @auther xianyue
 * @date 2021/10/4 - 星期一 - 17:05
 **/
// 方式1 : 继承 Thread 类，重写 run()，调用 start()
public class TestThread1 extends Thread {
    public static void main(String[] args) {
        TestThread1 testThread = new TestThread1();
        testThread.start();

        // 主线程
        for (int i=0; i < 20; i++) {
            System.out.println("我在学习多线程 " + i);
        }
    }

    @Override
    public void run() {
        for (int i=0; i < 20; i++) {
            System.out.println("我在看代码 " + i);
        }
    }
}
