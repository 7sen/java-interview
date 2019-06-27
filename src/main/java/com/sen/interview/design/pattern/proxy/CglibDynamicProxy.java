package com.sen.interview.design.pattern.proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * <p>
 * CGLIB 动态代理演示. <br/>
 * 动态代理是针对代理的类, 动态生成一个子类, 然后子类覆盖代理类中的方法,
 * 如果是private或是final类修饰的方法,则不会被重写。
 * </p>
 *
 * @author Leo
 * @date 2019-06-25 09:55
 */
public class CglibDynamicProxy {

    public static void main(String[] args) {
        // 生成 Cglib 代理类
        Woman proxy = (Woman) CglibProxy.getInstance(new Woman());
        // 调用相关方法
        proxy.eat();
        proxy.sleep();
    }
}

class Woman {

    public void eat() {
        System.out.println("我要吃肉...");
    }

    public void sleep() {
        System.out.println("我要睡觉...");
    }
}

class CglibProxy implements MethodInterceptor {

    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return method.invoke(target, objects);
    }

    public static Object getInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        // 设置需要代理的对象
        enhancer.setSuperclass(target.getClass());
        // 设置代理人
        enhancer.setCallback(new CglibProxy(target));
        return enhancer.create();
    }
}
