package com.design.pattern.controller;

import com.design.pattern.annotation.LoginRequired;
import com.design.pattern.annotation.MyLog;
import com.design.pattern.annotation.Test;
import com.design.pattern.config.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: pattern
 * @description: test controller
 * @author: Li Tong
 * @create: 2019-12-04 18:01
 **/
@RestController
public class TestController {

    /**
     * wo men bu yi yang
     * @return
     */

    /*@Autowired
    private TestConfiguration testConfiguration;

    public TestController(){
        this.abc="";
    }*/
    @Value("${my.env}")
    private String name;

    private final String abc="abc";

    @MyLog
    @RequestMapping(value = "/hello")
    public String sayHello(){
        return "hello";
    }


    @RequestMapping(value = "/hello/merge")
    public String sayHello1(){
        return "";
    }

    @LoginRequired(env = abc)
    @RequestMapping(value = "/hello/ana")
    public String ana(){
        return "hello";
    }
}
