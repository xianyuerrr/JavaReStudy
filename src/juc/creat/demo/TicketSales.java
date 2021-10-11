package juc.creat.demo;

/**
 * @auther xianyue
 * @date 2021/10/4 - 星期一 - 17:39
 **/
public class TicketSales implements Runnable {
    private int ticketNum = 10;

    public static void main(String[] args) {
        TicketSales ticketSales = new TicketSales();

        // 多个线程操作同一数据，数据一致性遭到破坏，线程不安全了
        new Thread(ticketSales, "A").start();
        new Thread(ticketSales, "B").start();
        new Thread(ticketSales, "C").start();
    }

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) break;

            // 双检锁机制，比 直接在方法上 + synchronized 好
            synchronized (TicketSales.class) {
                if (ticketNum <= 0) break;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " --> 拿到了 " + ticketNum-- + " 号票");
            }
        }
    }
}
