package java.baseContent.string;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 15:31
 **/

public class Str {
    public static void main(String[] args) {
//        String 不可变, StringBuilder 和 StringBuffer 是可变的
//        StringBuilde 没有对 writeObject 方法进行加同步锁,所以是非线程安全的
//        StringBuffer 对 writeObject 方法加了同步锁,所以是线程安全的。
        String s = "str";
        StringBuffer sbf = new StringBuffer("str");
        StringBuilder sbd = new StringBuilder("str");

        System.out.println(s);
        System.out.println(sbf);
        System.out.println(sbd);
//       printf 只能输出 String
//        System.out.printf(String.valueOf(sbf));
    }
}
