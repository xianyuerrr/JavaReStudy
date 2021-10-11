package juc.stopThread;

/**
 * @auther xianyue
 * @date 2021/10/10 - 星期日 - 17:43
 **/

// 建议线程正常停止 : 利用次数，而不建议使用死循环
// 建议使用标志位
// 不要使用 stop 和 destroy 等过时或 JDK 不推荐的方法
public class TestStop implements Runnable {
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run......Thread " + i++);
        }
    }

    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();

        new Thread(testStop).start();

        for (int i=0; i < 30; i++) {
            System.out.println("main + " + i);
            if (i == 20) {
                testStop.stop();
                System.out.println("线程该停止了！");
            }
        }
    }
}
