package com.sen.interview.juc.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：synchronized和Lock有什么区别?用新的lock有什么好处?请你举例说说
 * 1、原始构成
 * synchronized是关键字属于JVM层面，
 * monitorenter(底层是通过monitor对象来完成，其实wait/notify等方法也依赖monitor对象只有在同步代码块和同步方法中才能调用wait/notify等方法)
 * Lock是具体的类(java.util.concurrent.locks)，是api层面的锁
 *
 * 2、使用方法
 * synchronized不需要用户手动释放锁，synchronized代码执行完成以后系统会自动让线程释放对锁的占有
 * ReentrantLock则需要用户手动去释放锁，若没有主动释放锁，就有可能导致死锁现象。需要使用lock()和unlock()方法配合try finally语句块来完成。
 *
 * 3、等待是否可以中断
 * synchronized不可中断，除非抛出异常或者正常运行完成。
 * ReetrantLock可中断，
 * 　　1.设置超时方法tryLock(long timeout, TimeUnit unit);
 * 　　2.lockInterruptibly()放入代码块中，调用interrupt()方法可中断；
 *
 * 4、加锁是否公平
 * synchronized是非公平锁
 * ReentrantLock默认是非公平锁，可设置为公平锁。
 *
 * 5、锁绑定多个条件condition
 * synchronized没有；
 * ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个，要么唤醒全部线程。
 *
 * ===================================================================
 * 题目：多线程之间按找顺序调用，实现A->B->C三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次，重复上述过程10次.
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                Thread.currentThread().setName("A-" + i);
                shareResource.print5();
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                Thread.currentThread().setName("B-" + i);
                shareResource.print10();
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                Thread.currentThread().setName("C-" + i);
                shareResource.print15();
            }
        }).start();
    }

}

class ShareResource {

    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                condition1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 2;
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //判断
            while (number != 2) {
                condition2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //判断
            while (number != 3) {
                condition3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}