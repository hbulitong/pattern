package com.design.pattern.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String[]  value() default "unknown";
    String env() default "test";
}
