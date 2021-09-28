package reStudy.highContent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther xianyue
 * @date 2021/9/10 - 星期五 - 23:19
 **/

public class Annotation {
//    注解可以显式赋值，如果没有默认值，我们就必须给注解赋值
    @MyAnnotation(name = "秦疆")
    public void test() {
        
    }
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    // 注解的参数 : 参数类型 + 参数名 ();
    String name() default "";
}