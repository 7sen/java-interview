package com.shensen.interview.abc;

/**
 * <p>
 * 三个线程顺序打印ABC
 * </p>
 *
 * @author: Ryan
 * @date: 2024-04-17 17:44
 */
public class ABC1 {

    public static volatile int state = 1;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> ABC1.printA(), "A").start();
        new Thread(() -> ABC1.printB(), "B").start();
        new Thread(() -> ABC1.printC(), "C").start();
    }

    public static void printA() {
        while (state != 1) {
        }
        System.out.println("A");
        state++;
    }

    public static void printB() {
        while (state != 2) {
        }
        System.out.println("B");
        state++;
    }

    public static void printC() {
        while (state != 3) {
        }
        System.out.println("C");
        state++;
    }
}
