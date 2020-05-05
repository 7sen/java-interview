package com.shensen.interview.juc.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 题目：实现一个自旋锁:获取锁的线程不会立即阻塞，而是采用循环的方式获取锁
 * 好处：循环比较获取，直到成功为止，没有类似wait的等待
 * 通过CAS完成自旋锁，A线程先调用myLock方法，自己持有锁五秒钟，b线程进来发现当前线程有锁，
 * 只能线程等待，直到A释放后，B随即抢到
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-11 15:48
 */
public class SpinLockDemo {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    private void lock() {
        System.out.println(Thread.currentThread() + " coming...");
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
            // loop
        }
    }

    private void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread + " unlock...");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLock = new SpinLockDemo();
        new Thread(() -> {
            spinLock.lock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hahaha");
            spinLock.unlock();

        }, "A").start();

        Thread.sleep(1);

        new Thread(() -> {
            spinLock.lock();
            System.out.println("hehehe");
            spinLock.unlock();
        }, "B").start();
    }
}