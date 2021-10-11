package java2e.baseContent.innerClass;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 18:13
 **/

interface Inner {
    public void fun();
}

public class NoNameInnerClass {
    public static void main(String[] args) {
//        匿名内部类
//        使用匿名内部类，就必须继承一个父类或实现一个接口
        new Inner() {
            public void fun() {
                System.out.println("Hello World!");
            }
        }.fun();
    }

    void fun(int b) {
        int a = 10;
        new Thread() {
            public void run() {
                System.out.println(a);
                System.out.println(b);
            }
        }.start();
    }
}
