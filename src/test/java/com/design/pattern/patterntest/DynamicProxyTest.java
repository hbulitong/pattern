package com.design.pattern.patterntest;

import com.design.pattern.dynamicproxy.FacadeProxy;
import com.design.pattern.dynamicproxy.IHello;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: pattern
 * @description: 动态代理测试
 * @author: Li Tong
 * @create: 2019-11-25 15:05
 **/
public class DynamicProxyTest {
    public static void main(String[] args) {
        IHello hello= FacadeProxy.newProxyInstance(IHello.class);
                //newProxyInstance(IHello.class);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","yeqing");
        hello.dynamic(map);
    }


}
