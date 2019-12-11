package com.design.pattern.functiontest;

import java.util.function.Function;

/**
 * @program: pattern
 * @description: function test
 * @author: Li Tong
 * @create: 2019-11-15 15:36
 **/
public class FunctionTest<In,Out> {
    //R apply(T t);
    private Function<In,Out> processor=in -> {
        return (Out)new String("apply:"+in);
    };

    public static void main(String[] args) {
        FunctionTest<String,String > test=new FunctionTest<>();
        System.out.println(test.processor.apply("litong"));
    }
}
