package com.shensen.interview.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 阻塞队列的生产者消费者.
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1...循环操作5次.
 * </p>
 * 知识点：volatile/CAS/AtomicInteger/原子引用/BlockingQueue/线程交互
 *
 * @author Alwyn
 * @date 2019-06-12 17:21
 */
public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ProdConsumer prodConsumer = new ProdConsumer(new ArrayBlockingQueue<>(5));
        new Thread(() -> {
            try {
                prodConsumer.plus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                prodConsumer.minus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();

        TimeUnit.SECONDS.sleep(5);

        System.out.println(Thread.currentThread().getName() + " 老板叫停，收工结束.");

        prodConsumer.stop();
    }
}


class ProdConsumer {

    private volatile boolean flag = true;
    private AtomicInteger num = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public ProdConsumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void plus() throws InterruptedException {
        String data;
        boolean result;
        while (flag) {
            data = num.incrementAndGet() + "";
            result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + " 插入 " + data + " 到队列成功.");
            } else {
                System.out.println(Thread.currentThread().getName() + " 插入 " + data + " 到队列失败.");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "，生产队列结束.");
    }

    public void minus() throws InterruptedException {
        String result;
        while (flag) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (StringUtils.isNoneBlank(result)) {
                System.out.println(Thread.currentThread().getName() + " 消费 " + result + " 从队列成功.");
                System.out.println();
                System.out.println();
                continue;
            }
            flag = false;
        }
        System.out.println(Thread.currentThread().getName() + "，消费队列结束.");
    }

    public void stop() {
        flag = false;
    }
}