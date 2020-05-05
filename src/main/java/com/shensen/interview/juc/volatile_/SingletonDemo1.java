package com.shensen.interview.juc.volatile_;

/**
 * <p>
 * 基于静态内部类实现单例模式: 内部类 Holder将在Singleton初始化之后立刻初始化
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-17 09:59
 */
public class SingletonDemo1 {

    private static class Holder {

        static final SingletonDemo1 instance = new SingletonDemo1();
    }

    private SingletonDemo1() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法SingletonDemo（）");
    }

    public static SingletonDemo1 getInstance() {
        return Holder.instance;
    }

    public static void main(String[] args) {
        // 构造方法只会被执行一次
        // System.out.println(getInstance() == getInstance());
        // System.out.println(getInstance() == getInstance());
        // System.out.println(getInstance() == getInstance());

        // 构造方法会在一些情况下执行多次
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo1.getInstance();
            }, "Thread " + i).start();
        }
    }
}