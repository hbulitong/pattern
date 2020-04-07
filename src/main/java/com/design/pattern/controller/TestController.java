package com.design.pattern.controller;

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
    @RequestMapping(value = "/hello")
    public String sayHello(){
        return "hello";
    }

    @RequestMapping(value = "/hello/merge")
    public String sayHello1(){
        return "hello";
    }
}
