package com.design.pattern.vo;

import org.springframework.http.HttpStatus;

public class BaseResult<T> {
    private int code;
    private T body;
    private String msg;

    /**
     * @param code
     * @param body
     * @param msg
     */
    public BaseResult(int code, T body, String msg) {
        this.code = code;
        this.body = body;
        this.msg = msg;
    }

    public BaseResult(int code, T body) {
        this(code, body, null);
    }

    public BaseResult(int code) {
        this(code, null);
    }

    public BaseResult(T body, String msg) {
        this(HttpStatus.OK.value(), body, msg);
    }

    public BaseResult(T body) {
        this(HttpStatus.OK.value(), body);
    }

    public static void main(String[] args) {
        System.out.println(HttpStatus.OK.value());
    }


}
