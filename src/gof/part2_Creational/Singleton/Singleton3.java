package gof.part2_Creational.Singleton;

/**
 * @auther xianyue
 * @date 2021/9/29 - 星期三 - 11:29
 **/
// 双检锁
public class Singleton3 {
    private volatile static Singleton3 singleton;

    private Singleton3() {}

    public static Singleton3 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton3.class) {
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}
