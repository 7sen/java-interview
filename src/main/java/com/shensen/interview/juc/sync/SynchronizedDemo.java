package com.shensen.interview.juc.sync;

/**
 * <p>
 * synchronized可以保证方法或者代码块在运行时，同一时刻只有一个方法可以进入到临界区，同时它还可以保证共享变量的内存可见性
 * 使用对象：
 * 1、普通同步方法，锁是当前实例对象；
 * 2、静态同步方法，锁是当前类的class对象；
 * 3、同步代码块，锁是括号里面的对象（monitorenter、monitorexit）
 *
 * 利用 javap -c -s -v -l 命令查看底层原理.
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-21 14:54
 */
public class SynchronizedDemo {

    public synchronized void method1() {
        System.out.println("synchronized 普通同步方法.");
    }

    public synchronized static void method2() {
        System.out.println("synchronized 静态同步方法.");
    }

    public void method3() {
        synchronized (this) {
            System.out.println("synchronized 同步代码块.");
        }
    }

    public void method4() {
        synchronized (SynchronizedDemo.class) {
            System.out.println("synchronized 同步代码块.");
        }
    }
}