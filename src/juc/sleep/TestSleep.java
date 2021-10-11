package juc.sleep;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther xianyue
 * @date 2021/10/11 - 星期一 - 11:17
 **/

// sleep 存在异常 InterruptedException
// sleep 时间到达之后线程进入就绪态
// sleep 可以模拟网络延时，倒计时等，可以放大问题的发生性
// sleep 不会释放锁，wait 才会释放
public class TestSleep {
    public static void main(String[] args) {
        // 打印系统当前时间
        Date startTime = new Date(System.currentTimeMillis());
        System.out.println(startTime);
        System.out.println(startTime.getTime());
        int n = 5;
        while (n-- != 0) {
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime = new Date(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // try {
        //     tenDown();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }

    public static void tenDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(500);
            System.out.println(num--);
            if (num <= 0) {
                break;
            }
        }
    }
}
