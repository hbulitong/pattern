package com.design.pattern.annotation;

public class Person {
    @MyAnnotation
    @Deprecated
    public void empty(){
        System.out.println("empty");
    }
    @MyAnnotation(value = {"girl","boy"})
    public void someBody(String name,int age){
        System.out.println("somebody: "+name+", "+age);
    }
}
