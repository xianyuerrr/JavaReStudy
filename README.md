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
- 虚引用，也叫幻象引用，不能通过它来访问对象。仅仅是提供一种确保对象被 finalize 以后，做某些事情的机制，比如 Post-Mortem 清理机制
、Java 平台自身 Cleaner 机制

![可达性级别](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/D:/xianyue/Desktop/可达性级别.png)

![image-20211121000621424](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211121000621424.png)


### String、StringBuffer、StringBuilder

String 是 Java 非常基础的类，提供了构造和管理字符串的各种基本逻辑。是典型的 Immutable 类，final class，所有属性也都是 final 的,
无法对其内部进行修改。由于 Immutable 对象不可变，所以在拷贝构造时不需要额外复制数据。类似拼接、裁剪字符串都会产生 **新的 String 对象**。

StringBuffer 解决了使用 String 进行拼接字符串时会产生太多中间对象的问题。它本质上是一个线程安全的 **可修改字符序列**。其通过把各种
修改数据的方法都直接 **加上 synchronized 关键字** 来实现。但其在线程安全的同时也带来了额外的性能开销。所以除非有线程安全的需要，
不然还是推荐使用 StringBuilder，它和 StringBuffer 相比去掉了线程安全的部分，在保障功能的同时，有效减小了开销，是绝大部分情况下进
行字符串拼接的首选。

StringBuffer 和 StringBuilder 底层都是利用可修改的（char，JDK9 以后是 byte）数组，都继承了 AbstractStringBuilder，
里面包含了基本操作，区别仅在于方法是否加了 synchronized。

另外，这个内部数组应该创建成多大的呢？太小的话会在拼接时 **重新创建足够大的数组**；如果太大，又会浪费空间。
目前的实现是，构建 **初始字符串长度加16** 长度的数组。如果我们确定会发生多次拼接，而且大概是可预计的，那就可以指定合适的大小，
避免多次扩容的开销。

JDK1.8 中，虽然 String 是标准的不可变类，但是其 hash值 并没有使用 final 修饰，其 hash值 是在第一次调用 hashcode 方法时计算，
但是此方法未加锁，变量也没有使用 volatile 修饰以保证其可见性。当有多个线程调用的时候，
 hash值 可能会被计算多次（注意，多次计算的结果是一样的）。


### 动态代理

动态类型 与 静态类型 的区别在于：语言类型信息的检查是发生在 **运行时** 还是 **编译期**。

强类型 与 弱类型 的区别在于：不同类型变量赋值时，是否需要 **显式（强制）进行类型转换**。

通常认为，Java 是静态的强类型语言，但由于其提供了反射等机制，也具备了部分动态类型语言的能力。

动态代理是一种方便运行时动态构建代理、动态处理代理方法调用的机制，很多场景都是使用类似机制做到的，
比如用来包装 RPC 调用、面向切面编程（AOP）。

实现动态代理的方式有很多，比如 JDK 自身提供的动态代理，主要利用了反射机制。还有其他的实现方式，比如利用字节码操作机制，
类似 ASM、cglib、Javassit 等

动态代理，首先是一个代理机制。代理可以看作是对调用目标的一个包装，通过代理可以让调用者和实现者之间 **解耦**。比如进行 RPC 的调用、
框架内部的寻址、序列化、反序列化等。通过代理，可以提供更加友好的界面。

### int 和 Integer

