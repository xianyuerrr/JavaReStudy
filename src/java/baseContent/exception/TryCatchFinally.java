package java.baseContent.exception;

/**
 * @auther xianyue
 * @date 2021/9/7 - 星期二 - 20:17
 **/
public class TryCatchFinally {
    public static void main(String[] args) {
        TryCatchFinally tcf = new TryCatchFinally();
        int a = tcf.test1();
        System.out.println(a);
    }

    int test1() {
        int a = 1;
        try{
//            a = 2/0;
            System.out.println("test1 try");
            a = 2;
            return a;
        } catch (Exception e) {
            a = 3;
            System.out.println("test1 catch");
            return a;
        } finally {
//            修改这里的值并不会改变已经 return 回去的值
//            但是如果返回值是 引用类型，那么会修改其属性
            a = 4;
//            这里的return优先级很高
            return a;
        }

    }
}
