package com.shensen.interview.juc.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 传统的生产者消费者.
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1...循环操作5次.
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-12 17:21
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.plus();
            }
        }, "AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.minus();
            }
        }, "BBB").start();
    }
}


class ShareData {

    private int num;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void plus() {
        lock.lock();
        try {
            // 此处不可以使用if判断.
            while (num != 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "，当前num值：" + num);
            num++;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void minus() {
        lock.lock();
        try {
            while (num != 1) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "，当前num值：" + num);
            num--;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}