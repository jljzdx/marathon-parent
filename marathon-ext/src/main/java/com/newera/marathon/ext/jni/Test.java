package com.newera.marathon.ext.jni;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Mac JNI：
 * 1、生成.h头文件
 * cd /Users/microbin/Desktop/project/newera/marathon-parent/marathon-ext/target
 * javah -classpath classes -jni com.newera.marathon.ext.jni.Test
 * 2、编写C/C++（.c/.cpp）函数
 * 3、生成动态链接库（.jnilib）
 * cd /Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home/include/darwin
 * sudo cp -r jni_md.h ../jni_md.h
 * 将.h和.cpp放在同一个目录下，执行：
 * g++ -shared -I /Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/include JniTest.cpp -o libNativeCode.jnili
 * 4、加载动态库
 * System.load("/Users/microbin/Downloads/jni/libNativeCode.jnilib");
 *
 * 注意：
 * 编译C文件使用gcc：gcc T1.c T1.c -fPIC -shared -o libGoT.so
 * 编译C++文件使用的是g++：g++ T1.cpp T1.cpp -fPIC -shared -o libhello.so
 */
@Component
@Slf4j
public class Test {

    static {
        String osName = System.getProperty("os.name");
        log.info("系统 = [{}]",osName);
        System.load("/opt/jniLibPath/libNativeCode.jnilib");
    }
    public void setNum(int num){
        setNumNative(num);
        log.info("num = [{}]",num);
    }
    public int getNum(){
        int result = getNative();
        log.info("result = [{}]",result);
        return result;
    }
    public native void setNumNative(int num);  //链接库的方法
    public native int getNative();//链接库的方法
}
