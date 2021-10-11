package juc.join;

/**
 * @auther xianyue
 * @date 2021/10/11 - 星期一 - 16:24
 **/
// join 合并线程，其他线程阻塞。此线程执行完毕之后，再执行其他线程
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("线程 vip 来了 " + i);
        }
    }

    public static void main(String[] args) {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 20; i++) {
            if (i == 10) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main " + i);
        }

    }
}
