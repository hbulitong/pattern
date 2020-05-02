package com.design.pattern.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {
    public static void main(String[] args) throws Exception{
        Person person=new Person();
        Class<Person> p=Person.class;
        Method method=p.getMethod("someBody",new Class[]{String.class,int.class});
        method.invoke(person,new Object[]{"lili",18});
        iteratorAnnotations(method);

    }

    public static void iteratorAnnotations(Method method) {
        if(method.isAnnotationPresent(MyAnnotation.class)){
            MyAnnotation myAnnotation=method.getAnnotation(MyAnnotation.class);
            String value[]=myAnnotation.value();
            for(String item:value){
                System.out.println(item);
            }
        }
        Annotation[] annotations=method.getAnnotations();
        for(Annotation annotation : annotations){
            System.out.println(annotation);
        }

    }
}
