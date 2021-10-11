## juc

**Java 线程的状态**

1. NEW, 创建
2. RUNNABLE, 就绪、运行
3. BLOCKED, 阻塞
4. WAITING, 等待
5. TIMED_WAITING, 超时等待
6. TERMINATED, 终结


**常用方法**

- setPriority(int newPriority) : 更改线程优先级
- static void sleep(long millis) : 在指定毫秒数内让当前正在执行的线程休眠
- void join() : 等待该线程终止
- static void yield() : 暂停当前正在执行的线程，执行其他线程
- void interrupt() : 中断线程，一般不使用这个方法
- boolean isAlive() : 测试线程是否还处于活动状态


**守护线程（Daemon）**

虚拟机确保用户线程执行完毕，而不等待守护线程执行完毕