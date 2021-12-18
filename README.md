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

boolean, byte, short, char, int, float, double, long 是 Java 的八大原始数据类型。Java 虽然号称一切皆对象，但原始数据类型除外。

由于原始数据类型不能与 Java 泛型配合使用，所以对其进行了封装。
Integer 是 int 的包装类，有一个 int 类型的字段存储数据，而且提供了基本操作。Java 可以根据上下文，自动进行转换。

根据实践，大部分的数据操作都集中在较小的数值范围。因此，在 Java 5 中新增了静态工厂方法 valueOf，
在调用它时会利用缓存机制（默认为 -128 - 127，可以自行调整，缓存里的数据都是 **private final** 的）。

对象包括：
- 对象头
一般为 16 字节，包括两部分。
第一部分有哈希码、锁状态标志、线程持有的锁、偏向线程 id、gc 分代年龄等。
第二部分是类型指针，指向对象对应 class 对象的内存地址。

- 对象实例
- 对齐填充

自动装箱/拆箱是 Java 平台为保证不同写法在运行时等价而自动进行的一些转换，发生在 **编译阶段**，生成的字节码是一致的。

原则上，要避免 **无意义的装箱、拆箱**，尤其是在对性能敏感的场合，不管是内存使用还是处理速度，当对象数量变多时都相差极大。

### Vector、ArrayList、LinkedList

三者都是集合框架中的 List，功能类似但表现有很大不同。

- Vector
**线程安全**的动态数组，如果不需要线程安全，不建议选择。扩容时会提高 1 倍。

- ArrayList
与 Vector 相似，本身不是线程安全的，性能会好很多。扩容时会提高 0.5 倍。

- LinkedList
双向链表，线程不安全，不需要调整容量。

Vector 和 ArrayList 为动态数组，适合随机访问的场合。除了尾部插入和删除，往往性能会比较差。LinkedList 进行插入、删除要高效的多，
但是随机访问性能较差。

### HashTable、HashMap、TreeMap

<img src="https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211210233744397.png" alt="image-20211210233744397" style="zoom: 67%;" />

- HashTable 本身是同步的，不支持 null 键和值，由于开销较大，一般不推荐使用。
- HashMap 与 HashTable 大体一致，但它是不同步的，而且支持 null 键和值。
- TreeMap 基于红黑树，提供顺序访问，它的 get、put、remove 之类操作都是 O(log(n))的复杂度，具体顺序可以由指定的 Comparator 决定，或者根据键的自然顺序来判断。

HashMap 在并发环境可能出现 无限循环占用 CPU、size 不准确等诡异问题。HashMap 的性能表现非常依赖于哈希码的有效性。



Set 的几种实现:

TreeSet 支持自然顺序访问，但是添加、删除、包含等操作要相对低效（log(n) 时间）。

HashSet 则是利用哈希算法，理想情况下，如果哈希散列正常，可以提供常数时间的添加、删除、包含等操作，但是它不保证有序。

LinkedHashSet，内部构建了一个记录插入顺序的双向链表，因此提供了按照插入顺序遍历的能力，与此同时，也保证了常数时间的添加、删除、
包含等操作，这些操作性能略低于 HashSet，因为需要维护链表的开销。

在遍历元素时，HashSet 性能受自身容量影响，所以初始化时，除非有必要，
不然不要将其背后的 HashMap 容量设置过大。而对于 LinkedHashSet，由于其内部链表提供的方便，遍历性能只和元素多少有关系。

### 线程安全，ConcurrentHashMap

Java 提供了不同层面的线程安全支持。除了 HashTable 等同步内容，还提供了所谓的**同步包装器**(Synchronized Wrapper)，但是他们的锁粒度非常粗，在高并发情况下，性能比较低下。更加普遍的选择是利用并发包提供的线程安全容器类：

- 各种并发容器，比如 ConcurrentHashMap, CopyOnWriteArrayList
- 各种线程安全队列(Queue, Deque)，如 ArrayBlockingQueue, SynchronousQueue
- 各种有序容器的线程安全版本等

具体实现方式包括从简单的 synchronize 方式，到更加精细化的，比如基于分离锁实现的 ConcurrentHashMap等并发实现等。

早期 ConcurrentHashMap，其实现是基于：

- 分离锁，也就是将内部进行分段（Segment），里面则是 HashEntry 的数组，和 HashMap类似，哈希相同的条目也是以链表形式存放
- HashEntry 内部使用 volatile 的 value 字段来保证可见性，也利用了不可变对象的机制以改进利用 Unsafe 提供的底层能力，比如 volatile access，去直接完成部分操作，以最优化性能，毕竟 Unsafe 中的很多操作都是 JVM intrinsic 优化过的

<img src="https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211211185744744.png" alt="image-20211211185744744" style="zoom: 80%;" />



### IO

区分同步或异步（ synchronous / asynchronous）。简单来说，同步是一种可靠的有序运行机制，当我们进行同步操作时，后续的任务是等待当前调用返回，才会进行下一步。而异步则相反，其他任务不需要等待当前调用返回，通常依靠事件、回调等机制来实现仼务间次序关系。

区分阻塞与非阻塞（ blocking/non- blocking）。在进行阻塞操作时，当前线程会处于阻塞状态，无法从事其他任务，只有当条件就绪才能继续，比如 ServerSocket 新连接建立完毕，或数据读取、写入操作完成；而非阻塞则是不管 IO 操作是否结束，直接返回，相应操作在后台继续处理。

IO 不仅仅是对文件的操作，网络编程中，比如 Socket通信，都是典型的 IO 操作目标。

输入流、输出流（ InputStream / OutputStream）是用于读取或写入**字节**的，例如操作图片文件。而 Reader / Writer则是用于操作**字符**，增加了字符编解码等功能，适用于类似从文件中读取或者写入文本信息。本质上计算机操作的都是字节，不管是网络通信还是文件读取， Reader /  Writer 相当于构建了应用逻辑和原始数据之间的桥梁。

![image-20211211192612692](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211211192612692.png)

[NIO,美团技术团队](https://tech.meituan.com/2016/11/04/nio.html)



### 文件拷贝

- 基于输入输出流

实际上是进行了多次上下文切换，比如应用读取数据时，先在内核态将数据从磁盘读取到内核缓存，再切换到用户态将数据从内核缓存读取到用户缓存。所以，这种方式会带来一定的额外开销，可能会降低 IO 效率。


<img src="https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211211214746352.png" alt="image-20211211214746352" style="zoom:50%;" />

- 基于 NIO transferTo

会使用到零拷贝技术，数据传输并不需要用户态参与，省去了上下文切换的开销和不必要的内存拷贝，进而可能提高应用拷贝性能。注意， transferTo 不仅仅是可以用在文件拷贝中，与其类似的，例如读取磁盘文件，然后进行 Socket 发送，同样可以享受这种机制带来的性能和扩展性提高。

<img src="https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211211215025155.png" style="zoom:50%;" />


### 接口和抽象类

- 接口是对行为的抽象，它是抽象方法的集合，利用接口可以达到AP|定义和实现分离的目的。
接口，不能实例化；不能包含任何非常量成员，任何feld都是隐含着 public static fina的意义；
同时，没有非静态方法实现，也就是说要么是抽象方法，要么是静态方法。

- Java标准类库中，定义了非常多的接口，比如 java.util.List抽象类是不能实例化的类，用 abstract关键字修饰cass，其目的主要是代码重用。
除了不能实例化，形式上和一般的Java类并没有太大区别，可以有一个或者多个抽象方法，也可以没有抽象方法。抽象类大多用于抽取相关Java类
的共用方法实现或者是共同成员变量，然后通过继承的方式达到代码复用的目的。Java标准库中，比如 collection框架，
很多通用部分就被抽取成为抽象类，例如 java util. AbstractList.

相比于其他面向对象语言，Java 在设计上有一些基本区别，比如 Java 不支持多继承。在规范代码的同时，也产生了一些局限性


### 设计模式

[设计模式](./src/gof/gof.md)


## Java 进阶

### synchronized 和 ReentrantLock

线程安全是一个多线程环境下正确性的概念，也就是保证多线程环境下**共享的**、**可修改的**状态的正确性，
这里的状态反映在程序中其实可以看作是数据。

线程安全需要保证几个基本特性：
- 原子性，简单说就是相关操作不会中途被其他线程干扰，一般通过同步机制实现。
- 可见性，是—个线程修改了某个共享变量，其状态能够立即被其他线程知晓，通常被解释为将线程本地状态反映到主内存上， volatile就是负责保证可见性的。
- 有序性，是保证线程内串行语义，避免指令重排等。

synchronized 和 ReentrantLock 的性能不能一概而论，早期版本 synchronized 在很多场景下性能相差较大，在后续版本进行了较多改进，
在低竞争场景中表现可能优于 ReentrantLock。

如果使用 synchronized，我们根本无法进行公平性的选择，其永远是不公平的，这也是主流操作系统线程调度的选择。
通用场景中，公平性未必有想象中的那么重要，Java默认的调度策略很少会导致“饥饿”发生。与此同时，若要保证公平性则会引入额外开销，
自然会导致一定的吞吐量下降。所以，我建议只有当你的程序确实有公平性需要的时候，才有必要指定它。

![image-20211214172357844](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211214172357844.png)

为什么我们需要读写锁（ReadWriteLock）等其他锁呢？

这是因为，虽然 ReentrantLock 和 synchronized 简单实用，但是行为上有一定局限性，通俗点说就是“太霸道”，要么不占，要么独占。实际应用场景中，有的时候不需要大量竞争的写操作，而是以并发读取为主，如何进一步优化并发操作的粒度呢？

Java 并发包提供的读写锁等扩展了锁的能力，它所基于的原理是**多个读操作是不需要互斥的**，因为读操作并不会更改数据，所以不存在互相干扰。而**写操作则会导致并发一致性的问题**，所以写线程之间、读写线程之间，需要精心设计的互斥逻辑。



### synchronized 底层，锁的升降级

synchronized 代码段是由一对 monitorenter / monitorexit 指令实现，Monitor 对象是同步的基本实现单元。
Monitor 的实现完全依靠操作系统内部的互斥锁，由于需要从用户态切换到内核态，所以同步操作是**无差别的重量级操作**。

JVM 提供了三种不同的 Monitor 实现，也就是三种不同的锁：偏向锁(Biased Locking)、轻量级锁、重量级锁。
当 JVM 检测到不同的竞争状况时，会自动切换到适合的锁实现，这种切换就是锁的升、降级，是 JVM 对synchronized 的优化机制。

当没有竞争出现时，默认会使用偏斜锁。JVM会利用CAS 操作（compare and swap），在对象头上的 Mark Word 部分设置线程ID，
以表示这个对象偏向于当前线程，所以并不涉及真正的互斥锁。这样做的假设是基于在很多应用场景中，大部分对象生命周期中最多会被一个线程锁定，
使用偏斜锁可以降低无竞争开销。

如果有另外的线程试图锁定某个已经被偏斜过的对象，JVM就需要撤销（revoke）偏斜锁，并切换到轻量级锁实现。
轻量级锁依赖 CAS 操作 Mark Word 来试图获取锁，如果重试成功，就使用普通的轻量级锁；否则，进一步升级为重量级锁。

有的观点认为Java不会进行锁降级。实际上，锁降级确实是会发生的，当 JVM 进入安全点（SafePoint）时，会检查是否有闲置的 Monitor，然后试图进行降级。

自旋锁：竞争锁的失败的线程，并不会真实的在操作系统层面挂起等待，而是JVM会让线程做几个空循环（基于预测在不久的将来就能获得），
在经过若干次循环后，如果可以获得锁，那么进入临界区，如果还不能获得锁，才会真实的将线程在操作系统层面进行挂起。
优点是减少了上下文切换，缺点是消耗 cpu。注意，单 cpu 无效，因为基于 CAS 的轮询会占用 cpu 导致无法进行线程切换

![image-20211214171903307](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211214171903307.png)


### 一个线程两次调用 start() 方法

从操作系统的角度看，线程是系统调度的最小单元，一个进程可以包含多个线程，作为任务的真正运作者，
有自己的栈（Stack）、寄存器（Register）、本地存储（Thread Local）等，但是会和进程内其他线程共享文件描述符、虚拟地址空间等。

在具体实现中，线程还分为内核线程、用户线程，Java 的线程实现与虚拟机相关。对于我们最熟悉的 Sun/OracleJDK，
其线程也经历了一个演进过程，基本上在 Java 1.2之后，JDK 已经抛弃了所谓的 Green Thread，也就是用户调度的线程，
现在的模型是 **一对一映射到操作系统内核线程**。

Java 的线程是不允许启动两次的，第二次调用必然会抛出 IllegalThreadStateException，这是一种运行时异常，多次调用 start 被认为是编程错误。

[Java 线程](./src/juc/juc.md)

在第二次调用 start() 方法时，线程可能处于终止或者其他（非 NEW 状态），但是不论如何，都是不能再次启动的。

![image-20211214190654990](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211214190654990.png)

### 死锁的产生、定位、修复

![dl](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211214195224437.png)

- 首先，可以使用 jps 或者系统的 ps 命令、任务管理器等工具，确定进程 ID。

- 其次，调用 jstack 获取线程栈
```shell
jstack your_pid
```

- 分析得到的输出，如图：
![image-20211214195559631](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211214195559631.png)

- 最后结合代码分析线程栈信息

总体上可以理解为：
区分线程状态 -> 查看等待目标 -> 对比 Monitor 等持有状态


### Java 并发包工具类

java.util.concurrent 及其子包：

1. 比 synchronized 更加高级的各种同步结构，包括 CountDownLatch、CyclicBarrier、Semaphore 等，可以实现更加丰富的多线程操作，
比如利用 Semaphore 作为资源控制器，限制同时进行工作的线程数量。

2. 各种线程安全的容器，比如最常见的 ConcurrentHashMap、有序的 ConcunrrentSkipListMap，或者通过类似快照机制，
实现线程安全的动态数组 CopyOnWriteArrayList 等。

3. 各种并发队列实现，如各种 BlockedQueue 实现，比较典型的 ArrayBlockingQueue、SynchorousQueue
或针对特定场景的 PriorityBlockingQueue 等。

4. 强大的 Executor 框架，可以创建各种不同类型的线程池，调度任务运行等，绝大部分情况下，不再需要自己从头实现线程池和任务调度器。

- CountDownLatch, 允许一个或多个线程等待某些操作完成。
- CyclicBarrier, 一种辅助性的同步结构，允许多个线程等待到达某个屏障。
- Semaphore, Java 版本的信号量实现。


### ConcurrentLinkedQueue 和 LinkedBlockingQueue

严格来讲，类似 ConcurrentLinkedQueue 这种 “Concurrent” 容器，才真正代表并发。

区别：
- Concurrent 类型基于 lock-free，在常见的多线程访问场景，一般可以提供较高吞吐量。
- LinkedBlockingQueue 基于锁，并提供了 BlockingQueue 的等待性方法。

java.util.concurrent 包提供的容器（Queue、List、Set、Map），从命名上可以大概区分为 Concurrent、CopyOnWrite 和 Blocking 三类，
同样是线程安全容器，可以简单认为：
- Concurrent 类型没有 CopyOnWrite 类容器相对较重的修改开销。但是，Concurrent 往往提供了较低的遍历一致性。

弱一致性，可以理解为，当利用迭代器遍历时，即便容器发生修改，迭代器仍然可以继续进行遍历。
弱一致性的另外一个体现是，size 等操作准确性是有限的，未必是100%准确。与此同时，读取的性能具有一定的不确定性。

与弱一致性对应的，就是我介绍过的同步容器常见的行为 “fail-fast"，也就是如果检测到容器在遍历过程中发生了修改，
则抛出 ConcurrentModificationException，不再继续遍历。

![image-20211214235207018](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211214235207018.png)


### 线程池

Executors 目前提供了 5 种不同的线程池创建配置：
- newCachedThreadPool()，它是一种用来处理**大量短时间工作任务**的线程池。
具有几个鲜明特点：它会试图缓存线程并重用，当无缓存线程可用时，就会创建新的工作线程；
如果线程闲置的时间超过60秒，则被终止并移出缓存；长时间闲置时，这种线程池，不会消耗什么资源。其内部使用 SynchronousQueue 作为工作队列。

- newFixedThreadPool(int nThreads)，重用指定数目 (nThreads) 的线程，其背后使用的是无界的工作队列，
任何时候最多有 nThreads 个工作线程是活动的。这意味着，如果任务数量超过了活动队列数目，将在工作队列中等待空闲线程出现；
如果有工作线程退出，将会有新的工作线程被创建，以补足指定的数目 nThreads。

- newSingleThreadExecutor()，它的特点在于工作线程数目被限制为 1，操作一个无界的工作队列，所以它保证了所有任务的都是被**顺序执行**，
最多会有一个任务处于活动状态，并且不允许使用者改动线程池实例，因此可以避免其改变线程数目。

- newSingleThreadScheduledExecutor() 和 newScheduledThreadPool(int corePoolSize)，创建的是个 ScheduledExecutorService，
可以进行**定时或周期性的工作调度**，区别在于单一工作线程还是多个工作线程。

- newWorkStealingPool(int parallelism)，这是一个经常被人忽略的线程池，Java 8 才加入这个创建方法，其内部会构建 ForkJoinPool，
利用 Work-Stealing 算法，并行地处理任务，不保证处理顺序。

线程池这个定义就是个容易让人误解的术语，因为 ExecutorService 除了通常意义上“池”的功能，还提供了更全面的**线程管理**、**任务提交**等方法。

![image-20211215192855321](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211215192855321.png)

![image-20211215193022863](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211215193022863.png)

![image-20211215195104453](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211215195104453.png)


### AtomicInteger

AtomicIntger 是对 int 类型的一个封装，提供原子性的访问和更新操作，其原子性操作的实现是基于 CAS（compare-and-swap）技术。在大部分
处理器上 CAS 都是一个非常轻量级的操作。

CAS 也并不是没有副作用，试想，其常用的失败重试机制，隐含着一个假设，即竞争情况是短暂的。大多数应用场景中，
确实大部分重试只会发生一次就获得了成功，但是总是有意外情况，所以在有需要的时候，还是要考虑限制自旋的次数，以免过度消耗 CPU。

另外一个就是著名的 ABA 问题，这是通常只在 lock-free 算法下暴露的问题。我前面说过 CAS 是在更新时比较前值，如果对方只是恰好相同，
例如期间发生了 A->B->A 的更新，仅仅判断数值是 A，可能导致不合理的修改操作。针对这种情况，Java 提供了 AtomicStampedReference 工具类，
通过为引用建立类似版本号（stamp）的方式，来保证 CAS 的正确性。

AbstractQueuedSynchronizer(AQS)内部数据和方法可以简单拆分为：
- 一个 volatile 的整数成员表征状态，同时提供了 setState 和 getState 方法
- 一个先入先出（FIFO）的等待线程队列，以实现多线程间竞争和等待，这是 AQS 机制的核心之一。
- 各种基于 CAS 的基础操作方法，以及各种期望具体同步结构去实现的 acquire/release 方法。

利用 AQS 实现一个同步结构，至少要实现两个基本类型的方法，分别是 acquire 操作，获取资源的独占权；还有就是 release 操作，释放对某个资源的独占。


### 类加载过程，双亲委派模型

一般来说，我们把 Java 的类加载过程分为三个主要步骤：加载、链接、初始化。

首先是加载阶段（Loading），它是 Java 将字节码数据从不同的数据源读取到 JVM 中，并映射为JVM 认可的数据结构（Class对象），
这里的数据源可能是各种各样的形态，如 jar 文件、class 文件，甚至是网络数据源等；如果输入数据不是 ClassFile 的结构，
则会抛出 ClassFormatError。

第二阶段是链接（Linking），这是核心的步骤，简单说是把原始的类定义信息平滑地转化入 JVM 运行的过程中。这里可进一步细分为三个步骤：

- 验证（Verification），这是虚拟机安全的重要保障，JVM 需要核验字节信息是符合 Java 虚拟机规范，否则就被认为是 VerifyError，
这样就防止了恶意信息或者不合规的信息危害 JVM 的运行，验证阶段有可能触发更多 class 的加载。

- 准备（Preparation），创建类或接口中的静态变量，并初始化静态变量的初始值。但这里的“初始化”和下面的显式初始化阶段是有区别的，
侧重点在于分配所需要的内存空间，不会去执行更进一步的 JVM 指令。

- 解析（Resolution），在这一步会将常量池中的符号引用（symbolic reference）替换为直接引用。

最后是初始化阶段（initialization），这一步真正去执行类初始化的代码逻辑，包括静态字段赋值的动作，
以及执行类定义中的静态初始化块内的逻辑，编译器在编译阶段就会把这部分逻辑整理好，父类型的初始化逻辑优先于当前类型的逻辑。

双亲委派模型，简单说就是当类加载器（Class-Loader）试图加载某个类型的时候，除非父加载器找不到相应类型，
否则尽量将这个任务代理给当前加载器的父加载器去做。使用委派模型的目的是避免重复加载 Java 类型。

![image-20211216234812598](https://cdn.jsdelivr.net/gh/xianyuerrr/PicGo/img/Roaming/Typora/typora-user-images/image-20211216234812598.png)

通常类加载机制有三个基本特征：

- 双亲委派模型。但不是所有类加载都遵守这个模型，有的时候，启动类加载器所加载的类型，是可能要加载用户代码的，
比如 JDK 内部的 ServiceProvider/ServiceLoader 机制，用户可以在标准 API 框架上，提供自己的实现，JDK 也需要提供些默认的参考实现。
例如，Java 中 JNDI、JDBC、文件系统、Cipher 等很多方面，都是利用的这种机制，这种情况就不会用双亲委派模型去加载，而是利用所谓的上下文加载器。

- 可见性，子类加载器可以访问父加载器加载的类型，但是反过来是不允许的，不然，因为缺少必要的隔离，我们就没有办法利用类加载器去实现容器的逻辑。
- 单一性，由于父加载器的类型对于子加载器是可见的，所以父加载器中加载过的类型，就不会在子加载器中重复加载。
但是注意，类加载器“邻居”间，同一类型仍然可以被加载。


### 运行时动态生成类

通常的开发过程是，开发者编写 Java 代码，调用 javac 编译成 class 文件，然后通过类加载机制载入 JVM，就成为应用运行时可以使用的 Java 类了。

有一种笨办法，直接用 ProcessBuilder 之类启动 javac 进程，并指定上面生成的文件作为输入，进行编译。
最后，再利用类加载器，在运行时加载即可。这种方法本质上还是在当前程序之外编译的。

可以使用 Java Compiler APl，这是 JDK 提供的标准 API，里面提供了与 javac 对等的编译器功能，具体请参考 java.compiler 相关文档。

进一步思考，我们一直围绕 Java 源码编译成为 JVM 可以理解的字节码，换句话说，只要是符合 JVM 规范的字节码，不管它是如何生成的，
是不是都可以被JVM加载呢？我们能不能直接生成相应的字节码，然后交给类加载器去加载呢？当然也可以，不过直接去写字节码难度太大，
通常我们可以利用 Java 字节码操纵工具和类库来实现，比如在专栏第 6 讲中提到的 ASM、Javassist、cglib等。

对于一个普通的 Java 动态代理，其实现过程可以简化成为：
- 提供一个基础的接口，作为被调用类型（com.mycorp.Hellolmpl）和代理类之间的统一入口，如 com.mycorp.Hello。
- 实现 invocationHandler，对代理对象方法的调用，会被分派到其 invoke 方法来真正实现动作。
- 通过 Proxy 类，调用其 newProxylnstance 方法，生成一个实现了相应基础接口的代理类实例。动态代码就是在这个时候生成的。
