package java2e.highContent.lambda;

/**
 * @auther xianyue
 * @date 2021/10/10 - 星期日 - 16:30
 **/
public class TestLambda {
    // 3. 静态内部类
    static class Like2 implements ILike {
        @Override
        public void lambda(int i) {
            System.out.println("I Like" + i + " lambda.");
        }
    }

    public static void main(String[] args) {
        // 4. 局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda(int i) {
                System.out.println("I Like" + i + " lambda.");
            }
        }

        ILike iLike1 = new Like1();
        ILike iLike2 = new Like2();
        ILike iLike3 = new Like3();

        // 5. 匿名内部类
        ILike iLike4 = new ILike() {
            @Override
            public void lambda(int i) {
                System.out.println("I Like" + i + " lambda.");
            }
        };

        // 6. lambda 表达式
        ILike iLike5 = (int i) -> {
            System.out.println("I Like" + i + " lambda.");
        };

        // 7. lambda 简化，去掉 参数类型
        ILike iLike6 = (i) -> {
            System.out.println("I Like" + i + " lambda.");
        };

        // 8. lambda 简化，去掉参数括号（只有参数列表 只有一个参数时 才能去掉）
        ILike iLike7 = i -> {
            System.out.println("I Like" + i + " lambda.");
        };

        // 9. lambda 简化，去掉花括号（只有函数体为 一行 时才能去掉）
        ILike iLike8 = i -> System.out.println("I Like" + i + " lambda.");

        iLike1.lambda(1);
        iLike2.lambda(2);
        iLike3.lambda(3);
        iLike4.lambda(4);
        iLike5.lambda(5);
        iLike6.lambda(6);
        iLike7.lambda(7);
        iLike8.lambda(8);
    }
}

// 1. 定义函数式接口（只有一个方法）
interface ILike {
    public abstract void lambda(int a);
}

// 2. 实现类
class Like1 implements ILike {
    @Override
    public void lambda(int i) {
        System.out.println("I Like" + i + " lambda.");
    }
}