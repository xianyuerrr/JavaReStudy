package java.baseContent.polymorphism;

/**
 * @auther xianyue
 * @date 2021/9/4 - 星期六 - 16:20
 **/
public class Polymorphism {
    public static void main(String[] args) {
//      多态三个必要条件：继承、重写、对象上转型
        Shape circle = new Circle();
        Shape square = new Square();
        circle.draw();
        square.draw();
    }
}

class Shape {
    void draw() {}
}

class Circle extends Shape {
    void draw() {
        System.out.println("Circle.draw()");
    }
}

class Square extends Shape {
    void draw() {
        System.out.println("Square.draw()");
    }
}
