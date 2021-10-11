package juc.synchronize;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @auther xianyue
 * @date 2021/10/11 - 星期一 - 21:36
 **/
public class TestJUC {
    public static void main(String[] args) {
        // 安全的类型，JUC 下的 list
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
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
