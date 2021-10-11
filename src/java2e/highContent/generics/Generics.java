package java2e.highContent.generics;

import java.util.ArrayList;

/**
 * @auther xianyue
 * @date 2021/9/7 - 星期二 - 20:59
 **/
public class Generics {
    public static void main(String[] args) {
        ArrayList<Integer> al1 = new ArrayList<Integer>();
        ArrayList<String> al2 = new ArrayList<String>();

//        JVM 看到的只有 ListTest，看不到泛型信息
        System.out.println("al1 : " + al1.getClass());
        System.out.println("al2 : " + al2.getClass());
        System.out.println(al1.getClass() == al2.getClass());
    }
}
