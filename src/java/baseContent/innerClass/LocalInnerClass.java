package java.baseContent.innerClass;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 17:48
 **/
/*
内部类的优点
        1.内部类不为同一包的其他类所见，具有很好的封装性；
        2.匿名内部类可以很方便的定义回调
        3.每个内部类都能独立的继承一个接口的实现，
            所以无论外部类是否已经继承了某个（接口的）实现，
            对于内部类都没有影响
        4.内部类有效实现了”多重继承”，优化java单继承的缺陷
*/
public class LocalInnerClass {
    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        lic.test1();
        lic.test2();
    }

    private int dataA = 1;
    private  static int dataB = 2;

    void test1() {
        int innerData = 3;
//        局部内部类的访问仅限于方法内 或 作用域内
        class Inner {
            void fun() {
                System.out.println("dataA = " + dataA);
                System.out.println("dataB = " + dataB);
                System.out.println("innerData = " + innerData);
            }
        }
        Inner inner = new Inner();
        inner.fun();
    }

    static void test2() {
        int innerData = 3;
        class Inner {
            void fun() {
//                静态方法中的内部类不能访问非静态内容
//                System.out.println("dataA = " + dataA);
                System.out.println("dataB = " + dataB);
                System.out.println("innerData = " + innerData);
            }
        }
        Inner inner = new Inner();
        inner.fun();
    }
}
