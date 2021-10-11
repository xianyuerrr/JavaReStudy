package java2e.baseContent.override;

/**
 * @auther xianyue
 * @date 2021/9/2 - 星期四 - 23:11
 **/
public class Override {
    public static void main(String[] args) {
        Father f = new Son();
        Son s = new Son();
//        静态方法不能重写，静态方法在类加载时就装载了，属于类
//        重写的方法签名要一致
        f.test_static_method();
        f.test_method();

        s.test_static_method();
        s.test_method();
    }
}

class Father {
    static void test_static_method() {
        System.out.println("Father's static method!");
    }

    void test_method() {
        System.out.println("Father's method!");
    }
}

class Son extends Father {
    static void test_static_method() {
        System.out.println("Son's static method!");
    }

    @java.lang.Override
    void test_method() {
        System.out.println("Son's method!");
    }
}