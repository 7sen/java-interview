package com.sen.interview.juc.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 阻塞队列 SynchronousQueue 演示
 * </p>
 *
 * @author Leo
 * @date 2019-06-12 16:37
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1.");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2.");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3.");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " take " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
