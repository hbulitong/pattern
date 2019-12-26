package com.design.pattern.generic;

import lombok.Data;

@Data
public class Item<T> {
    private String name;
    private String value;
    T t;
    // 此处省略getter和setter方法
}
