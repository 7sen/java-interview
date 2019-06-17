package com.sen.interview.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 线程实现方式：
 * 1、实现 Runnable 接口. 通过 Thread 调用 start() 方法来启动线程.
 * 2、实现 Callable 接口. 与 Runnable 相比，可以有返回值，返回值通过 FutureTask 进行封装.
 * 3、继承 Thread 类.
 * 4、线程池.
 * </p>
 *
 * @author Leo
 * @date 2019-06-13 11:40
 */
public class ThreadRealizeDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread2());

        new Thread(futureTask, "AAA").start();
        // new Thread(futureTask, "BBB").start(); // 复用，直接取值，不要重启两个线程
        int a = 100;
        int b = 0;
        // b = futureTask.get(); // 要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成
        while (!futureTask.isDone()) {//当futureTask完成后取值
            b = futureTask.get();
        }
        System.out.println("Result：" + (a + b));
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable come in");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}
