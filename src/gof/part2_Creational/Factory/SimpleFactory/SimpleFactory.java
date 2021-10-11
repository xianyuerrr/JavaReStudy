package gof.part2_Creational.Factory.SimpleFactory;

import gof.part2_Creational.Factory.SimpleFactory.product.*;

/**
 * @auther xianyue
 * @date 2021/10/5 - 星期二 - 13:04
 **/
class SimpleFactory {
    public static IProduct makeProduct(int kind) {
        switch (kind) {
            case Const.PRODUCT_A:
                return new ProductA();
            case Const.PRODUCT_B:
                return new ProductB();
            case Const.PRODUCT_C:
                return new ProductC();
        }
        return null;
    }
}
