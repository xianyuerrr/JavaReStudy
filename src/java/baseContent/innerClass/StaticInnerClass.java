package java.baseContent.innerClass;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 18:16
 **/
public class StaticInnerClass {
    public static void main(String[] args) {
        StaticInnerClass.Inner inner = new StaticInnerClass.Inner();
    }

    static int dataA = 1;
    int dataB = 2;
    static class Inner {
        Inner() {
            System.out.println("dataA = " + dataA);
//            静态内部类是外部类的 静态成员，并不依赖于外部类对象
//            所以不能访问非 静态成员
//            System.out.println(dataB);
        }
    }
}
