package java2e.baseContent.innerClass;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 17:34
 **/
public class MemberInnerClass {
    public static void main(String[] args) {
//        成员内部类是依附外部类而存在的，
//        如果要创建成员内部类的对象，必须存在一个外部类对象
        MemberInnerClass ic = new MemberInnerClass();
        MemberInnerClass.Inner in = ic.new Inner();

        in.test();
    }

    private int data = 1;

    void fun() {
        System.out.println("这是外部类");
    }

//    成员内部类
    class Inner {
//        内部类不能有静态成员
//        static int a;
//        static viod test() {}

        Inner() {}

//        成员内部类可以访问外部类所有成员（属性和方法）
        void fun() {
            System.out.println("这是内部类");
            System.out.println(data);
        }

    //        成员同名时，可以使用 如下方法调用外部类成员
        void test() {
            fun();
            MemberInnerClass.this.fun();
        }
    }
}
