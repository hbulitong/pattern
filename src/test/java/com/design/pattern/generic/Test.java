package com.design.pattern.generic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

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

    private  <T> ResponseEntity<Map<String, T>> getRoushan3() {
        String json = "{\"code\":\"1\",\"msg\":\"Success\",\"data\":{\"orderid1\":{\"address\":\"street 1\",\"pay\":\"111.0\",\"productId\":\"1342546\"}}}";
        return null;
        //return JSONObject.parseObject(json, new TypeReference<ResponseEntity<Map<String, T>>>(){});
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
                (Result<List<String>>) transfer1(JSON.toJSONString(result2),"com.design.pattern.generic.Test","getRoushan1");
        log.info("hold");
        //getRoushan1 end

        ////getRoushan3
        Result result3=new Result<>();
        result3.setMsg("111");
        result3.setRet(00);
        Result ret2=
                (Result) transfer1(JSON.toJSONString(result3),"com.design.pattern.generic.Test","getRoushan2");
        log.info("hold");

        //
        String json = "{\"code\":\"1\",\"msg\":\"Success\",\"data\":{\"orderid1\":{\"address\":\"street 1\",\"pay\":\"111.0\",\"productId\":\"1342546\"}}}";
        ResponseEntity<Map<String, Map>> ret=(ResponseEntity<Map<String, Map>>)transfer1(json,"com.design.pattern.generic.Test","getRoushan3");
        log.info("hold");
    }

    //不能直接拿来用
    //Exception in thread "main" java.lang.ClassCastException: sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl cannot be cast to com.alibaba.fastjson.util.ParameterizedTypeImpl
    public static Object transfer1(String req,String className,String methodName ) throws  Exception{
        Class clazz=Class.forName(className);
        Method method=clazz.getDeclaredMethod(methodName);
        Type type=method.getGenericReturnType();
        if(type instanceof ParameterizedType){
            ParameterizedType type1=(ParameterizedType) type;
            ParameterizedTypeImpl beforeType = new ParameterizedTypeImpl(type1.getActualTypeArguments(), type1.getOwnerType(),type1.getRawType());
            return JSON.parseObject(req,beforeType);
        }else{  //没有泛型
            return JSON.parseObject(req,(Class)type);
        }
    }
}
