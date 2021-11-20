# JavaReStudy

## 总览

重学Java的记录

- Java 基础
- 数据结构类
- IO
- 注解和反射
- 多线程
- 设计模式

## Java 基础

### 对 Java 平台的理解
- 跨平台，通过 JVM 和 字节码 这种抽象，屏蔽了操作系统和硬件的细节，做到了书写一次，到处运行
- 垃圾收集，不需要自己操心内存的分配

Java 是解释和编译并存的语言。Java 源代码首先通过 Javac 编译成字节码(bytecode)，然后在运行时通过 Java 虚拟机(JVM) 内嵌的解释器将字
节码转换为最终的 机器码。

但是常见的 JVM，比如 Oracle JDK 提供的 Hotspot JVM，都会提供 JIT(Just in Time) 编译器，也就是动态编译器。能够在运行时将热点代码 (
运行时频率很高的那部分代码，根据二八定律，这部分代码虽然代码量不大，但系统 80% 以上的时间都在执行这段代码) 编译成机器码，
这部分热点代码就属于 **编译执行** 了，依此来提高效率。

### Exception 和 Error 的区别

两者都继承了 Throwable 类，可以被抛出 (throw) 和 捕获 (catch)。Exception 和 Error 体现了 Java 设计者对异常情况的分类。

- Exception 是指在正常运行中，可以预料的意外情况，应该被捕获并进行相应处理。
- Error 是指在正常情况下，不太可能出现的情况，绝大部分的 Error 都会导致程序处于非正常的、不可恢复的状态。所以不便于也不需要捕获，
比如 OutOfMemoryError 之类。

而 Exception 又分为 **可检查** 和 **不可检查** 两种异常。可检查异常必须在源码里显示地进行捕获处理，不然不能通过编译。
不可检查异常就是所谓的 **运行时异常**，比如 NullPointerException

![Exception 和 Error](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/D:/xianyue/Desktop/Snipaste_2021-11-17_00-13-03.png)

### final, finally, finalize
- final 可以原来修饰类、方法、变量，分别有不同的作用。

final 修饰的 class 不可被继承，final 修饰的方法不可被重写(override)，final 修饰的变量不能被修改。

很多核心类库以及第三方类库的源码中，相当一部分都被声明为 final class，可以有效避免使用者更改基础功能，以保证平台安全。
使用 final 修饰参数或者变量时，可以保护只读数据，省去一些防御性拷贝的必要

- finally 是 Java 保证重点代码一定要被执行的一种机制。

 可以使用 try-finally 或者 try-catch-finally 来进行类似关闭 JDBC 连接、保证 unlock 锁等动作

 - finalize 是 java.lang.Object 的一个方法，设计目的是保证对象在被垃圾回收前完成特定资源的回收，但现在已经不推荐使用，在 JDK9 被
 标记为 deprecated

### 强、软、弱、虚 四种引用

不同的引用类型，主要体现的是对象不同的可达性(reachable)状态和对垃圾收集的影响。

- 强引用(strong reference)，就是最常见的普通对象引用，只要还有强引用指向一个对象，那么这个对象就“活着”，不会被当作垃圾回收。
- 软引用(soft reference)，是相对强引用弱化一些的引用，可以让对象豁免垃圾回收，在 JVM 认为内存不足时才会进行回收，以防止 OOM(OutOfMemoryError)
- 弱引用(weak reference)，不能豁免垃圾回收，只是提供一种访问在弱引用状态下的对象的途径，是很多缓存实现的选择。
- 虚引用，也叫幻象引用，不能通过它来访问对象。仅仅是提供一种确保对象被 finalize 以后，做某些事情的机制，比如 Post-Mortem 清理机制、Java 平台自身 Cleaner 机制

![可达性级别](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/D:/xianyue/Desktop/可达性级别.png)





![image-20211121000621424](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211121000621424.png)



### String、StringBuffer、StringBuilder

