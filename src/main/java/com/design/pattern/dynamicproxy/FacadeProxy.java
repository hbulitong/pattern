package com.design.pattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: pattern
 * @description: 接口采用动态代理实现
 * @author: Li Tong
 * @create: 2019-11-25 14:59
 **/
public  class FacadeProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("接口方法调用开始");
        //执行方法
        System.out.println("method toGenericString:"+method.toGenericString());
        System.out.println("method name:"+method.getName());
        //获取入参类型
        Class<?>[] in=method.getParameterTypes();
        for(int i=0;i<in.length;i++){
            System.out.println(in[i].getName()+",");
            System.out.println("method args"+i+":"+in[i].cast(args[i]));
        }
        //获取出参类型
        Class<?> out=method.getReturnType();
        Map res=new HashMap<>();
        res.put("name","baigei");
        //out.cast(res);
        System.out.println("接口方法调用结束");
        return out.cast(res);
    }

    public static <T> T newProxyInstance(Class<T> jsfInterface){
        FacadeProxy proxy=new FacadeProxy();
        ClassLoader classLoader = jsfInterface.getClassLoader();
        Class<?>[] interfaces = new Class[]{jsfInterface};
        T t= (T)Proxy.newProxyInstance(classLoader,interfaces,proxy);
        return t;
    }
}
