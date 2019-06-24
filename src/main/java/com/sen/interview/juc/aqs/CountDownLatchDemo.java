package com.sen.interview.juc.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * CountDownLatch：主要有两个方法,当一个或多个线程调用await方法时,调用线程会被阻塞.
 * 其他线程调用countDown方法计数器减1(调用countDown方法时线程不会阻塞), 当计数器的值变为0,
 * 因调用await方法被阻塞的线程会被唤醒,继续执行
 * </p>
 *
 * @author Leo
 * @date 2019-06-11 17:16
 */
public class CountDownLatchDemo {

    private static final CountDownLatch countDownLatch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 离开了教室...");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长把门给关了，离开了教室...");
    }
}
