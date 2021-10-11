package juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther xianyue
 * @date 2021/10/11 - 星期一 - 21:57
 **/
public class TestReentrantLock {
    public static void main(String[] args) {
        TestLock testLock = new TestLock();
        new Thread(testLock).start();
        new Thread(testLock).start();
        new Thread(testLock).start();
    }
}

class TestLock implements Runnable {
    private int ticketNum = 10;
    private boolean flag = true;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (flag) {
            try {
                lock.lock();

                if (ticketNum > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNum--);
                } else {
                    flag = false;
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}