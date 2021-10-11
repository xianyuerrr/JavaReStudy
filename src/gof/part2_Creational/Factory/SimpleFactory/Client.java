package gof.part2_Creational.Factory.SimpleFactory;

import gof.part2_Creational.Factory.SimpleFactory.product.IProduct;

/**
 * @auther xianyue
 * @date 2021/10/5 - 星期二 - 11:32
 **/
public class Client {
    public static void main(String[] args) {
        IProduct product = new SimpleFactory().makeProduct(2);
        product.doSomething();
    }
}


