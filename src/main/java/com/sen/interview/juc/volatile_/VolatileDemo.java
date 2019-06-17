package com.sen.interview.juc.volatile_;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 可见性代码示例.
 * 1、验证volatile的可见性
 * 1.1 如果int num = 0，number变量没有添加volatile关键字修饰
 * 1.2 添加了volatile，可以解决可见性
 * </p>
 *
 * @author Leo
 * @date 2019-06-14 14:58
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addSelf();
                    myData.atomicAddSelf();
                }
            }, "Thread " + i).start();
        }
        // 等待上面的线程都计算完成后，再用main线程取得最终结果值
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally num value is " + myData.num);
        System.out.println(Thread.currentThread().getName() + "\t finally atomicnum value is " + myData.atomicInteger);
    }
}

class MyData {

    // int num = 0;
    volatile int num = 0;

    public synchronized void addToSixty() {
        this.num = 60;
    }

    public void addSelf() {
        num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void atomicAddSelf() {
        atomicInteger.getAndIncrement();
    }
}