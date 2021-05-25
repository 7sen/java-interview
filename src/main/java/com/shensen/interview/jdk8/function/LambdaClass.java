package com.shensen.interview.jdk8.function;

/**
 * Java 8 允许使用 :: 关键字来传递方法或者构造函数引用，无论如何，表达式返回的类型必须是 functional-interface。
 *
 * @author Alwyn
 * @date 2021-05-25 06:48
 */
public class LambdaClass extends LambdaClassSuper {

    public static LambdaInterface staticF() {
        return null;
    }

    public LambdaInterface f() {
        return null;
    }

    void show() {
        //1.调用静态函数，返回类型必须是functional-interface
        LambdaInterface t = LambdaClass::staticF;

        //2.实例方法调用
        LambdaClass lambdaClass = new LambdaClass();
        LambdaInterface lambdaInterface = lambdaClass::f;

        //3.超类上的方法调用
        LambdaInterface superf = super::sf;

        //4. 构造方法调用
        LambdaInterface tt = LambdaClassSuper::new;
    }

}