package juc.synchronize;

/**
 * @auther xianyue
 * @date 2021/10/11 - 星期一 - 17:10
 **/
public class UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "账户");

        Drawing drawing1 = new Drawing(account, 50, "a");
        Drawing drawing2 = new Drawing(account, 100, "b");

        drawing1.start();
        drawing2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("账户余额 : " + account.money);
    }
}

class Account {
    int money; // 余额
    String name; // 卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread {
    Account account; // 账户
    int drawingMoney; // 取了多少钱
    int nowMoney; // 现在有多少钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        if (account.money - drawingMoney < 0) {
            System.out.println(Thread.currentThread().getName() + " 钱不够，无法取钱");
            return;
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account.money -= drawingMoney;
        nowMoney += drawingMoney;

        System.out.println(account.name + " 余额 : " + account.money);
        System.out.println(this.getName() + " 手里的钱 : " + nowMoney);
    }
}