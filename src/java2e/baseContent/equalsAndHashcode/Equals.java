package java2e.baseContent.equalsAndHashcode;

/**
 * @auther xianyue
 * @date 2021/9/5 - 星期日 - 10:15
 **/
public class Equals {
    public static void main(String[] args) {
        int a = 1, b = 1;
        Node1 n1 = new Node1(), n2 = new Node1();

//        对于基本数据类型， == 比较值
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a == b : " + (a == b));

//        对于引用类型， == 比较的是 hashcode() 的返回值
        System.out.println("n1 = " + n1);
        System.out.println("n2 = " + n2);
        System.out.println("n1 == n2 : " + (n1 == n2));
        System.out.println("n1.equalsAndHashcode(n2) : " + (n1.equals(n2)));
    }
}

class Node1 {
    int val=1;
//    未重写时，equalsAndHashcode() 和 == 作用相同
    boolean equals(Node1 n) {
        if (n == null) {
            return false;
        }
        if (! (n instanceof Node1)) {
            return false;
        }
        if (this == n || this.val == n.val) {
            return true;
        } else {
            return false;
        }
    }
}

class Node2 extends Node1 {
    @Override
    public int hashCode() {
        return this.val;
    }
}