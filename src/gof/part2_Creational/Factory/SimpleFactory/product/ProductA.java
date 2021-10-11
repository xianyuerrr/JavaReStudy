package gof.part2_Creational.Factory.SimpleFactory.product;

/**
 * @auther xianyue
 * @date 2021/10/5 - 星期二 - 12:49
 **/
public class ProductA implements IProduct {
    @Override
    public void doSomething() {
        System.out.println("I am Product A.");
    }
}