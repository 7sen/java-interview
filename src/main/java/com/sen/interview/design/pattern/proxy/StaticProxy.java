package com.sen.interview.design.pattern.proxy;

/**
 * <p>
 * 静态代理演示.<br/>
 * 要求被代理类和代理类同时实现相应的一套接口，通过代理类调用重写接口的方法，
 * 实际上调用的是原始对象的同样的方法。
 * </p>
 *
 * @author Leo
 * @date 2019-06-25 10:38
 */
public class StaticProxy {

    public static void main(String[] args) {
        Person person = new Man();
        Person proxy = new PersonProxy(person);
        proxy.sleep();
        proxy.eat();
    }
}

class PersonProxy implements Person {

    private Person person;

    public PersonProxy(Person person) {
        this.person = person;
    }

    @Override
    public void eat() {
        person.eat();
    }

    @Override
    public void sleep() {
        person.sleep();
    }
}