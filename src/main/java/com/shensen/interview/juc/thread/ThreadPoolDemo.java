package com.shensen.interview.juc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 线程第四种实现方式：线程池管理线程.
 * 优点：
 * 1、降低资源消耗；
 * 2、提高响应速度；
 * 3、提高线程的可管理性。
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-13 15:56
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 15; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务.");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    /**
     * 使用Executors创建线程池，实际开发中不推荐使用，使用new ThreadPoolExecutor()创建.
     */
    private static void executors() {
        // 一池固定线程
        // ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 一池单线程
        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 一池多线程
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务.");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
