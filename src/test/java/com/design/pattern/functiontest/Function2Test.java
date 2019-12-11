package com.design.pattern.functiontest;

import java.util.function.Function;

/**
 * @program: pattern
 * @description: function test
 * @author: Li Tong
 * @create: 2019-11-15 15:55
 **/
public class Function2Test {
    public static void main(String[] args) {
        Function<Integer,Integer> name=integer -> integer*2;
        Function<Integer,Integer> square=integer -> integer*integer;
        System.out.println(name.andThen(square).apply(3));  //36,先执行自己的apply
        System.out.println(name.compose(square).apply(3));  //18,先执行after的apply
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object o=Function.identity().apply("hoho");
        System.out.println(o);
    }
}
