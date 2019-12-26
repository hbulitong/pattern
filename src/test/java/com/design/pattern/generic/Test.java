package com.design.pattern.generic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
public class Test {
    public Result<Item<String>> getRoushan(){
        return null;
    }

    public Result<List<String>> getRoushan1(){
        return null;
    }
    public Result getRoushan2(){
        return null;
    }

    public static void main(String[] args) throws  Exception{
        //getRoushan
        Item<String> item=new Item<>();
        item.setName("dddd");
        item.setValue("ddd");
        item.setT("bbb");
        log.info(JSON.toJSONString(item));
        Result<Item<String>> result=new Result<>();
        result.setMsg("111");
        result.setRet(00);
        result.setData(item);
        log.info(JSON.toJSONString(result));
        String resultStr=JSON.toJSONString(result);
        //getRoushan1
        List<String> list=new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        Result<List<String>> result2=new Result<>();
        result2.setMsg("111");
        result2.setRet(00);
        result2.setData(list);

        Result<List<String>> ret1=
                (Result<List<String>>) transfer(JSON.toJSONString(result2),"com.design.pattern.generic.Test","getRoushan1");
        log.info("hold");
        //getRoushan1 end

        ////getRoushan3
        Result result3=new Result<>();
        result3.setMsg("111");
        result3.setRet(00);
        Result ret2=
                (Result) transfer(JSON.toJSONString(result3),"com.design.pattern.generic.Test","getRoushan2");
        log.info("hold");
    }

    public static Object transfer(String req,String className,String methodName ) throws  Exception{
        Class clazz=Class.forName(className);
        Method method=clazz.getDeclaredMethod(methodName);
        Queue<Class> queue=new LinkedList<>();
        Type type=method.getGenericReturnType();
        if(type instanceof ParameterizedType){
            queue.add((Class)((ParameterizedType) type).getRawType());
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type type1 =parameterizedType.getActualTypeArguments()[0];

            while(type1 instanceof ParameterizedType ){
                queue.add((Class)((ParameterizedType) type1).getRawType());
                Type type2=((ParameterizedType) type1).getActualTypeArguments()[0];
                type1=type2;
            }
            queue.add((Class)type1);
        }else{  //没有泛型
            return JSON.parseObject(req,(Class)type);
        }
        log.info(String.valueOf(queue.size()));
        log.info("hold...");
        Class classes[]=new Class[queue.size()];
        for(int i=0;i<classes.length;i++){
            classes[i]=queue.poll();
        }
        //这里不用TypeReference方式，直接用class对象来处理

        ParameterizedTypeImpl beforeType = null;
        if (classes.length!=0){
            //支持多级泛型的解析
            for (int i = classes.length-1; i >0 ; i--) {
                beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null? classes[i]:beforeType}, null, classes[i - 1]);
            }
        }

        return JSON.parseObject(req,beforeType);

    }
    //不能直接拿来用
    //Exception in thread "main" java.lang.ClassCastException: sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl cannot be cast to com.alibaba.fastjson.util.ParameterizedTypeImpl
    public static Object transfer1(String req,String className,String methodName ) throws  Exception{
        Class clazz=Class.forName(className);
        Method method=clazz.getDeclaredMethod(methodName);
        Queue<Class> queue=new LinkedList<>();
        Type type=method.getGenericReturnType();
        if(type instanceof ParameterizedType){
            ParameterizedTypeImpl beforeType = (ParameterizedTypeImpl)type;
            return JSON.parseObject(req,beforeType);
        }else{  //没有泛型
            return JSON.parseObject(req,(Class)type);
        }




    }
}
