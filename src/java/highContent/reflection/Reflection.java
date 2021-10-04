package java.highContent.reflection;

/**
 * @auther xianyue
 * @date 2021/9/7 - 星期二 - 19:46
 **/
public class Reflection {
    public static void main(String[] args) {
//        反射动态加载类，代码灵活度高
//        但是性能比直接的 Java 代码慢很多


//        方式1,getClass() 方法是 Object 类的方法
        Person p1 = new Person();
        Class c1 = p1.getClass();

//        方式2
        Class c2 = Person.class;

//        方式3，可能会抛出 ClassNotFoundException 错误
        try {
//            一个类在内存中只有一个 Class 对象
//            一个类被 加载 之后，类的整个结构都会被封装在 Class 对象中
            Class c3 = Class.forName("java.highContent.reflection.Reflection");
            Class c4 = Class.forName("java.highContent.reflection.Reflection");
            System.out.println(c3==c4);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println(p1);
        System.out.println(p1.hashCode());

        System.out.println(c1);
        System.out.println(c2);
    }
}

class Person {
    String name = "Li";
    Person() {}
}
