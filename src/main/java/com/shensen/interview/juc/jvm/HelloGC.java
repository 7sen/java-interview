package com.shensen.interview.juc.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.HashMap;
import javax.print.DocFlavor.STRING;

/**
 * <p>
 * 题目：如何查看一个正在运行java程序，某个jvm参数是否开启，具体值多少
 * 步骤如下：
 * 1、jps -l
 * 2、jinfo -flag PrintGCDetails 2204
 * ===============结果===============
 * -XX:-PrintGCDetails
 * ===============结果===============
 *
 * JVM参数：
 * 1、-Xms（-XX:InitialHeapSize）：初始大小内存，默认为物理内存1/64
 * 2、-Xmx（-XX:MaxHeapSize）：最大分配内存，默认为物理内存1/4
 * 3、-Xss（-XX:ThreadStackSize）：设置单个线程的大小，一般默认为512K~1024K
 * 4、-Xmn：设置年轻代大小
 * 5、-XX:MetaspaceSize：设置元空间大小
 * 6、-XX:+PrintGCDetails：输出详细GC收集日志信息
 * 7、-XX:SurvivorRatio：配置新生代（Eden、From Survivor、To Survivor）比例，默认8:1:1
 * 8、-XX:NewRatio：配置新生代与年老代在堆结构中比例，默认1:2
 * 9、-XX:MaxTenuringThreshold：设置垃圾最大年龄
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-18 12:33
 */
public class HelloGC {

    public static void main(String[] args) {
        byte[] array = new byte[300 * 1024 * 1024]; // 734,003,200
        for (MemoryPoolMXBean memoryPoolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println(
                    memoryPoolMXBean.getName() + "  总量:" + memoryPoolMXBean.getUsage().getCommitted() + "   使用的内存:"
                            + memoryPoolMXBean.getUsage().getUsed());
        }
    }

    /**
     * 演示java.lang.StackOverflowError现象.
     */
    private static void showStackOverflowError() {
        showStackOverflowError();
    }

    /**
     * 演示java.lang.OutOfMemoryError: Java heap space现象.
     * VM options:-Xms10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
     */
    private static void showOutOfMemoryError() {
        byte[] array = new byte[50 * 1024 * 1024];
    }

    private static void showXmxAndXmx() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY（-Xms）= " + totalMemory + " 字节、"
                + (totalMemory / (double) (1024 * 1024)) + " MB.");
        System.out.println("MAX_MEMORY（-Xmx）= " + maxMemory + " 字节、"
                + (maxMemory / (double) (1024 * 1024)) + " MB.");
    }

    private static void showJpsAdnJinfo() {
        System.out.println("hello GC...");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}