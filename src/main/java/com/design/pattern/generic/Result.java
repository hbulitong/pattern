package com.design.pattern.generic;

import lombok.Data;

@Data
public class Result<T> {
    private int ret;
    private String msg;
    private T data;
    // 此处省略getter和setter方法
}
