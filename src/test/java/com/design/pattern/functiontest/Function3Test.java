package com.design.pattern.functiontest;

import com.design.pattern.functiontest.interfaces.NoReturnOneParam;
import com.design.pattern.functiontest.interfaces.ReturnOneParam;

/**
 * @program: pattern
 * @description: function test
 * @author: Li Tong
 * @create: 2019-11-15 18:27
 **/
public class Function3Test {
    public static void main(String[] args) {
        NoReturnOneParam noReturnOneParam =a ->
            System.out.println("NoReturnOneParam param:" + a);

        noReturnOneParam.method(1);

        ReturnOneParam lambda4= a->a+4;
    }
}
