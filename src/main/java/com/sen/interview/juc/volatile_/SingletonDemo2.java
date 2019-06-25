package com.sen.interview.juc.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 饿汉式单例模式演示.
 * </p>
 *
 * @author Leo
 * @date 2019-06-17 09:59
 */
public class SingletonDemo2 {

    private static SingletonDemo2 singleton = new SingletonDemo2();

    private SingletonDemo2() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法SingletonDemo2（）");
    }

    public static SingletonDemo2 getInstance() {
        return singleton;
    }

    public static void main(String[] args) {
        // 构造方法只会被执行一次
        System.out.println(getInstance() == getInstance());
        System.out.println(getInstance() == getInstance());
        System.out.println(getInstance() == getInstance());

        // 构造方法会在一些情况下执行多次
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo2.getInstance();
            }, "Thread " + i).start();
        }
    }
}


/**
 * <p>
 * 懒汉式单例模式演示.
 * </p>
 *
 * @author Leo
 * @date 2019-06-17 09:59
 */
class SingletonDemo3 {

    private static SingletonDemo3 singleton = null;

    private SingletonDemo3() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法SingletonDemo3（）");
    }

    public static SingletonDemo3 getInstance() {
        // synchronized (SingletonDemo3.class) {
        if (null == singleton) {
            // 模拟懒汉式线程不安全问题.
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleton = new SingletonDemo3();
        }
        // }
        return singleton;
    }

    public static void main(String[] args) {
        // 构造方法只会被执行一次
        // System.out.println(getInstance() == getInstance());
        // System.out.println(getInstance() == getInstance());
        // System.out.println(getInstance() == getInstance());

        // 构造方法会在一些情况下执行多次
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo3 singleton = SingletonDemo3.getInstance();
                System.out.println(null == singleton);
            }, "Thread " + i).start();
        }
    }
}