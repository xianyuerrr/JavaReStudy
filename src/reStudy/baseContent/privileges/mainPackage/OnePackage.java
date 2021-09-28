package reStudy.baseContent.privileges.mainPackage;

/**
 * @auther xianyue
 * @date 2021/9/5 - 星期日 - 9:55
 **/
public class OnePackage {
    public static void main(String[] args) {
        TestPrivileges tp = new TestPrivileges();
//        同包只有 private 不能被访问
        System.out.println(tp.def);
        System.out.println(tp.prt);
        System.out.println(tp.pub);
    }
}
class Son extends TestPrivileges{
    void fun() {
        TestPrivileges tp = new TestPrivileges();
//        同包只有 private 不能被访问
        System.out.println(tp.def);
        System.out.println(tp.prt);
        System.out.println(tp.pub);
    }
}