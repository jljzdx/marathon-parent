package com.newera.marathon.base.demo.classloader;

import java.util.Arrays;

/**
 * @author MicroBin
 * @description：TODO
 * @date 2020/7/29 8:36 下午
 */
public class AllClassLoader
{
    /**
     * 输出类加载器
     */
    public static void classLoader(){
        System.out.println(AllClassLoader.class.getClassLoader());
        System.out.println(AllClassLoader.class.getClassLoader().getParent());
    }
    /**
     * 输出应用类加载器路径
     */
    public static void appClassLoader(){
        String paths = System.getProperty("java.class.path");
        Arrays.asList(paths.split(":")).forEach(System.out::println);
    }
    /**
     * 输出扩展类加载器路径
     */
    public static void extClassLoader(){
        String paths = System.getProperty("java.ext.dirs");
        Arrays.asList(paths.split(":")).forEach(System.out::println);
    }
    /**
     * 输出启动类加载器路径
     */
    public static void bootClassLoader(){
        String paths = System.getProperty("sun.boot.class.path");
        Arrays.asList(paths.split(":")).forEach(System.out::println);
    }
    public static void main(String[] args)
    {
        //bootClassLoader();
        //extClassLoader();
        //appClassLoader();
        classLoader();
    }
}
