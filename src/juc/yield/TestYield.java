package juc.yield;

/**
 * @auther xianyue
 * @date 2021/10/11 - 星期一 - 16:14
 **/
// 礼让，不一定成功，看 CPU 心情
public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();

        new Thread(myYield, "a").start();
        new Thread(myYield, "b").start();
        // 存在如下情况
        // a线程开始执行
        // a线程停止执行
        // b线程开始执行
        // b线程停止执行
    }
}

class MyYield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程停止执行");
    }
}