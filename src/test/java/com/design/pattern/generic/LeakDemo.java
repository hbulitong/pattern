package com.design.pattern.generic;

/**
 * 内存泄露测试
 * ParameterizedTypeImpl
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;


public class LeakDemo {

    public static void main(String[] args) {
        /*
        -Xms30m
        -Xmx30m
        -XX:+PrintGCDateStamps
        -XX:+PrintGCDetails
        -XX:+PrintHeapAtGC
        -XX:+PrintGCApplicationStoppedTime
        -Xloggc:/tmp/gc_%p_%t_.log
        -XX:+HeapDumpOnOutOfMemoryError
        -XX:HeapDumpPath=/tmp/
         */
        final long start = System.currentTimeMillis();
        final AtomicLong counter = new AtomicLong(0);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("count: " + counter.get());
                System.out.println("took " + (System.currentTimeMillis() - start) + " ms");
            }
        }));

        SomeInfo someInfo = new SomeInfo();
        someInfo.setName("Tom");
        CommonVO<SomeInfo> result = new CommonVO<>();
        result.setData(someInfo);
        result.setRetCode(0);
        result.setMessage("Success");

        String json = JSON.toJSONString(result);

        // 模拟业务中不断的接口请求处理
        while (true) {
            ParameterizedTypeImpl type = new ParameterizedTypeImpl(new Type[]{SomeInfo.class}, null, CommonVO.class);
            CommonVO<SomeInfo> tmpResult = (CommonVO<SomeInfo>) JSON.parseObject(json, type);
            Objects.requireNonNull(tmpResult);

            counter.incrementAndGet();
            System.out.println(counter.get());

        }
    }

    public static class CommonVO<T> {
        private int retCode;
        private String message;
        private T data;

        public int getRetCode() {
            return retCode;
        }

        public void setRetCode(int retCode) {
            this.retCode = retCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public static class SomeInfo {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
