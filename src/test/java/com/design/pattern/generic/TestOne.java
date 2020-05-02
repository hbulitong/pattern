package com.design.pattern.generic;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class TestOne {
    public static void main(String[] args) throws Exception{
        Class clazz1=Class.forName("com.design.pattern.generic.Test");
        Method method1=clazz1.getDeclaredMethod("getRoushan");
        Type type1=method1.getGenericReturnType();
        Class clazz2=Class.forName("com.design.pattern.generic.Test");
        Method method2=clazz2.getDeclaredMethod("getRoushan");
        Type type2=method2.getGenericReturnType();
        System.out.println("hold...");
    }
}
