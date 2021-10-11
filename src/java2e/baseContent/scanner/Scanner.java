package java2e.baseContent.scanner;

/**
 * @auther xianyue
 * @date 2021/8/31 - 星期二 - 20:55
 **/
public class Scanner {
    public static void main(String[] args) {
        // write your code here
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        if (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
//            System.out.println(Scanner.next());
        } else {
            System.out.println("非Int");
        }

//        if (Scanner.hasNext()) {
//            String str = Scanner.next();
//            System.out.println(str);
//        }
        scanner.close();
    }
}