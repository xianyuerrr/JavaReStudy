package java2e.baseContent.logic;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 15:35
 **/
public class Logic {
    public static void main(String[] args) {
        test_and();
        test_or();
    }

    static void test_or() {
        int i = 0, j = 0;
        if (i == 0 || j++ > 0) {}
        System.out.println("i = " + i + " j = " + j);

        if (i == 0 | j++ > 0) {}
        System.out.println("i = " + i + " j = " + j);

    }

    static void test_and() {
        int i = 0, j = 0;
        if (i != 0 && j++ > 0) {}
        System.out.println("i = " + i + " j = " + j);

        if (i != 0 & j++ > 0) {}
        System.out.println("i = " + i + " j = " + j);
    }
}


