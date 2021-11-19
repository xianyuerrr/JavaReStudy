package java2e.baseContent.exception;

/**
 * @auther xianyue
 * @date 2021/11/19 - 星期五 - 23:57
 **/
public class TestException {
    public static void main(String[] args) {
        try {
            // 业务代码
            Thread.sleep(1000L);
        } catch (Exception e) {
            //Ignore it
        }
        // 虽然这么写也能运行，但是不利于阅读和debug
        // 此段代码违反了两个基本原则
        // 1. 尽量不要捕获类似 Exception 这样的通用异常，而是应该捕获特定的异常，方便更准确的发现和处理bug
        // 而且，除非已经想好了，不然不要捕获 Throwable 和 Error，除非你确定能够正确处理他们
        // 2. 不要生吞(swallow)异常。如果我们不把异常抛出，也没有将其输出到日志，程序可能以不可控的方式结束，
        // 没人知道是哪里出了异常，以及为什么出的异常

    }
}
