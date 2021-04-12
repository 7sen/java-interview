package com.shensen.interview.juc.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("A...");
            return null;
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B...");
            return null;
        });
        // 将他们合并起来
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
        allFutures.join();
        System.out.println("allFutures done!");
    }

}
