package juc.synchronize;

/**
 * @auther xianyue
 * @date 2021/10/11 - 星期一 - 17:01
 **/
// 不安全的买票
public class UnSafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket, "a").start();
        new Thread(buyTicket, "b").start();
        new Thread(buyTicket, "c").start();
    }
}

class BuyTicket implements Runnable {
    private int ticketNum = 10;
    boolean flag = true;

    public void buy() {
        if (ticketNum <= 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 拿到 " + ticketNum--);
    }

    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }
}
