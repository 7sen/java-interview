package com.sen.interview.juc.design.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * JDK动态代理演示. <br/>
 * java.lang.reflect.Proxy:生成动态代理类和对象；
 * java.lang.reflect.InvocationHandler（处理器接口）：可以通过invoke方法实现.
 *
 * 对真实角色的代理访问。
 * 每次通过 Proxy 生成的代理类对象都要指定对应的处理器对象。
 * </p>
 *
 * @author Leo
 * @date 2019-06-25 09:55
 */
public class JDKDynamicProxy {

    public static void main(String[] args) {
        Person person = new Man();
        PersonInvocationHandler invocationHandler = new PersonInvocationHandler(person);
        Person proxy = (Person) Proxy
                .newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Person.class}, invocationHandler);
        proxy.sleep();
        proxy.eat();
    }
}

interface Person {

    public void eat();

    public void sleep();
}

class Man implements Person {

    @Override
    public void eat() {
        System.out.println("我要吃肉...");
    }

    @Override
    public void sleep() {
        System.out.println("我要睡觉...");
    }
}

class PersonInvocationHandler implements InvocationHandler {

    private Person person;

    public PersonInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(person, args);
    }
}