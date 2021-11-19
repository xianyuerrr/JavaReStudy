package java2e.baseContent.exception;

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
            // System.exit(1); 如果执行了此句，那么之后的代码就都不会执行了，包括 finally 内的代码
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
