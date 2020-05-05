package com.shensen.interview.juc.jvm;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * <p>
 * 演示 java.long.OutOfMemoryError 异常.
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-19 09:21
 */
public class OOMDemo {

    public static void main(String[] args) {
    }

    /**
     * java.lang.OutOfMemoryError: Java heap space
     * VM options：-Xms10m -Xmx10m
     */
    private static void showOutOfMemoryError1() {
        byte[] bytes = new byte[50 * 1024 * 1024];
    }

    /**
     * java.lang.OutOfMemoryError: GC overhead limit exceeded
     * VM options：-Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m
     */
    private static void showOutOfMemoryError2() {
        int i = 0;
        List<String> list = new ArrayList<>();

        while (true) {
            list.add(String.valueOf(++i));
        }
    }

    /**
     * java.lang.OutOfMemoryError: Direct buffer memory
     * VM options：-Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m
     */
    private static void showOutOfMemoryError3() {
        ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }

    /**
     * Java.lang.OutOfMemeoryError:unable to create new native thread
     * 代码需要跑在Linux环境下，Linux系统非root用户默认创建线程个数1024
     * 配置文件路径：/etc/security/limits.d/20-nproc.conf
     */
    private static void showOutOfMemoryError4() {
        for (int i = 0; ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i + "").start();
        }
    }

    static class OOMTest {

    }

    /**
     * java.lang.OutOfMemoryError: Metaspace.
     * VM options：-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
     */
    private static void showOutOfMemoryError5() {

        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
                            throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("第 " + i + " 次发生异常.");
        }

    }

}