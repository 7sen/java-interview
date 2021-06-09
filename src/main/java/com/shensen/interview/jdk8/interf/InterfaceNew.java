package com.shensen.interview.jdk8.interf;

/**
 * Interface:
 * 1、default修饰的方法，是普通实例方法，可以用this调用，可以被子类继承、重写
 * 2、static修饰的方法，使用上和一般类静态方法一样。但它不能被子类继承，只能用Interface调用。
 *
 * @author Alwyn
 * @date 2021-06-09 21:01
 */
public interface InterfaceNew {

    static void eat() {
        System.out.println("接口方法可以用 static 修饰");
    }

    default void fly() {
        System.out.println("接口方法可以用 default 修饰");
    }

    // 方式必须要重写
    void f();
}

interface InterfaceNew1 {

    default void fly() {
        System.out.println("接口方法可以用 default 修饰");
    }
}

class InterfaceNewImpl implements InterfaceNew, InterfaceNew1 {

    // 多实现，同名方法必须被重写，否则编译报错
    @Override
    public void fly() {
        InterfaceNew.super.fly();
    }

    @Override
    public void f() {

    }
}