package java2e.baseContent.staticUse;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 15:42
 **/
public class StaticUse {
//    静态代码块，只会在类加载时运行一次，一般用来初始化
    static {
        System.out.println("Hello World!");
    }

//    静态变量，被所有实例共享，在类加载时初始化
    public static String s1 = "s1";
    String s2 = "s2";

    void fun1() {
        System.out.println(s1);
        System.out.println(s2);
    }

//    静态方法，不依赖于对象，只能调用静态方法、访问静态变量
    static void fun2() {
        System.out.println(s1);
//        静态方法不能调用非静态变量
//        System.out.println(s2);
    }

}
