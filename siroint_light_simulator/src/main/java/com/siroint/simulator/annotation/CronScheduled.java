package com.siroint.simulator.annotation;

import java.lang.annotation.*;

/**
 * @author xmdeng
 * @date 2021/8/2 15:30
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CronScheduled  {

    /**
     * 执行表达式
     * @return
     */
    String cron() default "";

    /**
     * 描述
     * @return
     */
    String desc() default "";
}
