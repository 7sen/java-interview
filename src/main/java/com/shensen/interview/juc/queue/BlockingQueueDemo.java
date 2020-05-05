package com.shensen.interview.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 当阻塞队列是空时，从队列中获取元素的操作将会被阻塞。
 * 当阻塞队列是满时，往队列里添加元素的操作将会被阻塞。
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-12 15:50
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
    }

    /**
     * Summary of BlockingQueue methods：Times out
     * 当阻塞队列满时,队列会阻塞生产者线程一定时间,超过后限时后生产者线程就会退出
     *
     * @throws InterruptedException
     */
    private static void timesOut() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("A", 1, TimeUnit.SECONDS);
        blockingQueue.offer("B", 1, TimeUnit.SECONDS);
        blockingQueue.offer("C", 1, TimeUnit.SECONDS);
        // blockingQueue.offer("D", 1, TimeUnit.SECONDS);

        blockingQueue.poll(1, TimeUnit.SECONDS);
        blockingQueue.poll(1, TimeUnit.SECONDS);
        blockingQueue.poll(1, TimeUnit.SECONDS);
        // blockingQueue.poll(1, TimeUnit.MILLISECONDS);
    }

    /**
     * Summary of BlockingQueue methods：Blocks
     * 当阻塞队列满时,生产者继续往队列里面put元素,队列会一直阻塞直到put数据or响应中断退出
     * 当阻塞队列空时,消费者试图从队列take元素,队列会一直阻塞消费者线程直到队列可用.
     *
     * @throws InterruptedException
     */
    private static void blocks() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("A");
        blockingQueue.put("B");
        blockingQueue.put("C");
        blockingQueue.put("D");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
    }

    /**
     * Summary of BlockingQueue methods：Special value
     * 插入方法,成功返回true 失败返回false
     * 移除方法,成功返回元素,队列里面没有就返回null
     */
    private static void SpecialValue() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("A");
        blockingQueue.offer("B");
        blockingQueue.offer("C");
        System.out.println(blockingQueue.offer("D"));

        System.out.println(blockingQueue.peek());

        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        System.out.println(blockingQueue.poll());
    }

    /**
     * Summary of BlockingQueue methods：Throws exception
     * 1、当阻塞队列满时,再往队列里面add插入元素会抛IllegalStateException: Queue full
     * 2、当阻塞队列空时,再往队列Remove元素时候回抛出NoSuchElementException
     */
    private static void throwsException() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("A");
        blockingQueue.add("B");
        blockingQueue.add("C");
        // 1、blockingQueue.add("D");

        System.out.println(blockingQueue.element());

        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
        // 2、blockingQueue.remove();
    }
}
