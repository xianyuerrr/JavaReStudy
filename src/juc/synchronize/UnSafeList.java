package juc.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther xianyue
 * @date 2021/10/11 - 星期一 - 17:30
 **/

// 线程不安全的集合
public class UnSafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> list.add(Thread.currentThread().getName())).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
    }
}
