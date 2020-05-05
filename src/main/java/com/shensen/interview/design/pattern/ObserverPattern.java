package com.shensen.interview.design.pattern;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 * 观察者模式演示.</br>
 * 定义：定义对象间一种一对多的依赖关系，使得当每一个对象改变状态，
 * 则所有依赖于它的对象都会得到通知并自动更新。
 * 类型：行为类模式
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-25 09:00
 */
public class ObserverPattern {

    public static void main(String[] args) {
        Subject sub = new ConcreteSubject();
        sub.addObserver(new ConcreteObserver1()); //添加观察者1
        sub.addObserver(new ConcreteObserver2()); //添加观察者2
        sub.doSomething();
    }
}

// 被观察者
abstract class Subject {

    private List<Observer> obs = new CopyOnWriteArrayList<>();

    public void addObserver(Observer obs) {
        this.obs.add(obs);
    }

    public void delObserver(Observer obs) {
        this.obs.remove(obs);
    }

    protected void notifyObserver() {
        for (Observer o : obs) {
            o.update();
        }
    }

    public abstract void doSomething();
}

// 具体被观察者
class ConcreteSubject extends Subject {

    public void doSomething() {
        System.out.println("被观察者事件反生");
        this.notifyObserver();
    }
}

// 观察者
interface Observer {

    public void update();
}

// 具体观察者1
class ConcreteObserver1 implements Observer {

    public void update() {
        System.out.println("观察者1收到信息，并进行处理。");
    }
}

// 具体观察者2
class ConcreteObserver2 implements Observer {

    public void update() {
        System.out.println("观察者2收到信息，并进行处理。");
    }
}