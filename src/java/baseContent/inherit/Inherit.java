package java.baseContent.inherit;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 16:09
 **/
public class Inherit {
    public static void main(String[] args) {
//        Father f = new Father();
        Son s = new Son("Lisa", 20);
    }
}

class Father {
    static {
        System.out.println("父亲");
    }
    String name;
//    Java程序存在继承，在执行子类的构造方法时，
//    如果没有用 super() 来调用父类特定的构造方法，
//    则会调用父类中没有参数的构造方法"。
//    如果父类只定义了有参数的构造函数，
//    而子类的构造函数没有用super调用父类那个特定的构造函数，
//    就会出错
    Father() {
        System.out.println("父类的无参构造");
    }
    Father(String name) {
        System.out.println("父类有名字");
    }
}

class Son extends Father {
    static {
        System.out.println("儿子");
    }
    int age;
    Son() {
//        默认会调用父类的无参构造
        super();
        System.out.println("子类的无参构造");
    }

    Son(String name) {
        super(name);
        System.out.println("子类有名字");
    }

    Son(String name, int age) {
        this(name);
        System.out.println("子类有名字和年龄");
    }
}