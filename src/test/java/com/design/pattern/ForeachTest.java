package com.design.pattern;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class ForeachTest {
    public static void main(String[] args) {
        List<String> list= Lists.newArrayList("A","B","C","D","E");
        list.forEach(item->{
            if("B".equals(item)) log.info("got you!");
        });
        testConsumer();
       // testAndThen();

        Function<Integer,Integer> A= i->i+1;
        Function<Integer,Integer> B=i->i*i;
        //((3+1)+1)
        System.out.println(B.compose(A).compose(A).andThen(A).apply(3));

    }

    public static void  testConsumer(){
        Consumer<Integer> square=x-> log.info("print square:{}",x*x);
        square.accept(2);
    }

    public static void testAndThen(){
        Consumer<Integer> consumer1 = x -> System.out.println("first x : " + x);
        Consumer<Integer> consumer2 = x -> {
            System.out.println("second x : " + x);
            throw new NullPointerException("throw exception test");
        };
        Consumer<Integer> consumer3 = x -> System.out.println("third x : " + x);

        consumer1.andThen(consumer2).andThen(consumer3).accept(1);

    }
}
