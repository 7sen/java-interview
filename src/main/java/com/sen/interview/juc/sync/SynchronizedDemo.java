package com.sen.interview.juc.sync;

/**
 * <p>
 * Synchronized关键字.
 * 1、使用方式：1、修饰实例方法；2、修饰静态方法；3、修饰代码块
 * 2、利用 javap -c -s -v -l 命令查看底层原理.
 * </p>
 *
 * @author Leo
 * @date 2019-06-21 14:54
 */
public class SynchronizedDemo {

    public void methodA() {
        synchronized (this) {
            System.out.println("synchronized 代码块.");
        }
    }

    public synchronized void methodB() {
        System.out.println("synchronized 方法.");
    }

    public void methodC() {
        synchronized (SynchronizedDemo.class) {
            System.out.println("synchronized 代码块.");
        }
    }
}