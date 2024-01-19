package com.shensen.interview.abc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 三个线程顺序打印ABC
 * </p>
 *
 * @author: Ryan
 * @date: 2024-04-17 17:44
 */
public class ABC2 {

    public static final Lock LOCK = new ReentrantLock();

    // 判断是否执行：1表示应该A执行，2表示应该B执行，3表示应该C执行
    public static volatile int state = 1;

    public static Condition a = LOCK.newCondition();
    public static Condition b = LOCK.newCondition();
    public static Condition c = LOCK.newCondition();


    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> ABC2.printA(), "A").start();
        new Thread(() -> ABC2.printB(), "B").start();
        new Thread(() -> ABC2.printC(), "C").start();
    }

    public static void printA() {
        try {
            LOCK.lock();
            while (state != 1) {
                a.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("A");
            }
            state = 2;
            b.signal();
        } catch (InterruptedException e) {
        } finally {
            LOCK.unlock();
        }
    }

    public static void printB() {
        try {
            LOCK.lock();
            while (state != 2) {
                b.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("B");
            }
            state = 3;
            c.signal();
        } catch (InterruptedException e) {
        } finally {
            LOCK.unlock();
        }
    }

    public static void printC() {
        try {
            LOCK.lock();
            while (state != 3) {
                c.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println("C");
            }
            state = 1;
            a.signal();
        } catch (InterruptedException e) {
        } finally {
            LOCK.unlock();
        }
    }
}
