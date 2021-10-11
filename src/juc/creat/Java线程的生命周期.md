## Java线程的生命周期

![img](https://raw.githubusercontent.com/xianyuerrr/PicGo/main/img/202110041715371.jpeg)

## 线程的优先级

每一个 Java 线程都有一个优先级，这样有助于操作系统确定线程的调度顺序。
Java 线程的优先级是一个整数，其取值范围是 1 （Thread.MIN_PRIORITY ） - 10 （Thread.MAX_PRIORITY ）。
默认情况下，每一个线程都会分配一个优先级 NORM_PRIORITY（5）。

具有较高优先级的线程对程序更重要，并且应该在低优先级的线程之前分配处理器资源。但是，线程优先级不能保证线程执行的顺序，而且非常依赖于平台。

## 创建线程

Java 提供了三种创建线程的方法：
- 通过继承 Thread 类本身；
- 通过实现 Runnable 接口(避免了单继承的局限性)；
- 通过 Callable 和 Future 创建线程(使用线程池，有返回值，可抛出异常)。

