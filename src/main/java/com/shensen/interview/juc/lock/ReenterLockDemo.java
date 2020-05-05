package com.shensen.interview.juc.lock;

class Phone {

    public synchronized void sendSms() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\tsendSms");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\tsendEmail");
    }

}

/**
 * <p>
 * 可重入锁(也叫做递归锁)
 * 指的是同一先生外层函数获得锁后,内层递归函数任然能获取该锁的代码
 * 在同一线程外外层方法获取锁的时候,在进入内层方法会自动获取锁
 * 也就是说,线程可以进入任何一个它已经标记的锁所同步的代码块
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-11 12:46
 */
public class ReenterLockDemo {

    /**
     * t1 sendSms
     * t1 sendEmail
     * t2 sendSms
     * t2 sendEmail
     *
     * @param args
     */
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}


