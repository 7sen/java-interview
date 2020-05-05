package com.shensen.interview.design.pattern;

import com.shensen.interview.juc.volatile_.SingletonDemo2;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 单例模式演示.<br/>
 * 定义：确保一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。
 * 类型：创建类模式
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-17 09:59
 */
public class SingletonPattern {

    public static void main(String[] args) {
        // 构造方法只会被执行一次
        System.out.println(BadmashSingleton.getInstance() == BadmashSingleton.getInstance());
        System.out.println(BadmashSingleton.getInstance() == BadmashSingleton.getInstance());
        System.out.println(BadmashSingleton.getInstance() == BadmashSingleton.getInstance());

        // 构造方法会在一些情况下执行多次
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo2.getInstance();
            }, "Thread " + i).start();
        }
    }
}

// 饿汉式单例模式演示.
class BadmashSingleton {

    private static BadmashSingleton singleton = new BadmashSingleton();

    private BadmashSingleton() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法BadmashSingleton（）");
    }

    public static BadmashSingleton getInstance() {
        return singleton;
    }

}

// 懒汉式单例模式演示.
class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法LazySingleton（）");
    }

    public static LazySingleton getInstance() {
        // synchronized (LazySingleton.class) {
        if (null == singleton) {
            // 模拟懒汉式线程不安全问题.
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleton = new LazySingleton();
        }
        // }
        return singleton;
    }
}