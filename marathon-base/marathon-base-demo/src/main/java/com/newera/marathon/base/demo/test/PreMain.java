package com.newera.marathon.base.demo.test;

import java.lang.instrument.Instrumentation;

/**
 * @author MicroBin
 * @description：TODO
 * @date 2020/8/5 3:44 下午
 */
public class PreMain
{
    public static void premain(String agentArgs, Instrumentation instrumentation){
        System.out.println("====premain 方法执行");
        System.out.println(agentArgs);
    }
}
