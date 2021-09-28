package reStudy.baseContent.equalsAndHashcode;

import java.util.HashSet;

/**
 * @auther xianyue
 * @date 2021/9/5 - 星期日 - 21:48
 **/
public class Hashcode {
    public static void main(String[] args) {
        HashSet<Test1> h1 = new HashSet<Test1>();
        HashSet<Test2> h2 = new HashSet<Test2>();
        Test1 a = new Test1(1);
        Test1 b = new Test1(1);
        Test2 c = new Test2(2);
        Test2 d = new Test2(2);

        h1.add(a);
        h1.add(b);
        h2.add(c);
        h2.add(d);

//        重写 equals() 之后，必须重写 hashCode() 方法，不然会哈希失效

//        有两个元素，哈希失效
        for (Test1 t : h1) {
            System.out.println(t.val);
        }
//        只有 1 个元素，哈希成功
        System.out.println("*");
        for (Test1 t : h2) {
            System.out.println(t.val);
        }

    }
}

class Test1 {
    int val;

    Test1() {}
    Test1(int val) {
        this.val = val;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return false;
        }
        if (obj instanceof Test1) {
            return this.val == ((Test1)obj).val;
        }
        return false;
    }
}

class Test2 extends Test1 {
    Test2() {}
    Test2(int val) {
        super(val);
    }

    @Override
    public int hashCode() {
        return this.val;
    }
}


