package com.design.pattern.patterntest;

import com.design.pattern.builder.Producer;

/**
 * @program: pattern
 * @description: Builder Test
 * @author: Li Tong
 * @create: 2019-11-25 14:18
 **/
public class BuilderTest {
    public static void main(String[] args) {
        Producer producer=new Producer.Builder()
                                        .id(1)
                                        .name("yeqing")
                                        .price(123)
                                        .type(2)
                                        .build();
        System.out.println(producer);
    }
}
