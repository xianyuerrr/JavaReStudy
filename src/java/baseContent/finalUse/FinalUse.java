package java.baseContent.finalUse;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 15:52
 **/

//用 final 修饰的类不能被继承
final class Father {
    int age;
    String name;

//    final 修饰的的方法不能被重写
    final void study() {}
}
//
//class Son extends Father {
//    final void study() {}
//}

public class FinalUse {
    public static void main(String[] args) {
//        基础类型
        int a = 1;
        final int b, c = -1;

        a = 2; b = 2;
        System.out.println(a);
        System.out.println(b);
//        c = 2;

//        引用类型
        final Father f = new Father();
//        f = new Father();
        f.age = 10;
//        不能修改引用类型的值，但可以修改其指向的对象的值

//        final 修饰的变量 赋值之后 不能被修改
//        也就是仅可以赋一次值
    }
}

