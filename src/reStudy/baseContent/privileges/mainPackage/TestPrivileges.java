package reStudy.baseContent.privileges.mainPackage;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 18:59
 **/
public class TestPrivileges {
    public static void main(String[] args) {
        TestPrivileges tp = new TestPrivileges();
//        同类内都可访问
        System.out.println(tp.prt);
        System.out.println(tp.def);
        System.out.println(tp.prt);
        System.out.println(tp.pub);
    }
    private int pri;
    int def;
    protected int prt;
    public int pub;
}
