package com.sen.interview.juc.design.pattern;

/**
 * <p>
 * 抽象工厂模式演示.<br/>
 * 定义：为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类。
 * 类型：创建类模式
 * </p>
 *
 * @author Leo
 * @date 2019-06-25 09:30
 */
public class AbstractFactoryPattern {

    public static void main(String[] args) {
        IFactory factory = new Factory();
        factory.createProduct1().show();
        factory.createProduct2().show();
    }
}

interface IProduct1 {

    public void show();
}

interface IProduct2 {

    public void show();
}

class Product1 implements IProduct1 {

    public void show() {
        System.out.println("这是1型产品");
    }
}

class Product2 implements IProduct2 {

    public void show() {
        System.out.println("这是2型产品");
    }
}

interface IFactory {

    public IProduct1 createProduct1();

    public IProduct2 createProduct2();
}

class Factory implements IFactory {

    public IProduct1 createProduct1() {
        return new Product1();
    }

    public IProduct2 createProduct2() {
        return new Product2();
    }
}