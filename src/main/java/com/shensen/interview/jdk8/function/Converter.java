package com.shensen.interview.jdk8.function;

/**
 * 定义：也称 SAM 接口，即 Single Abstract Method interfaces，有且只有一个抽象方法，
 * 但可以有多个非抽象方法的接口。
 * jdk8大部分函数接口都在{@link java.util.function}包下面
 * 可以隐式转换Lambda表达式。Lambda 表达式是一个匿名函数，java 8 允许把函数作为参数传递进方法中
 *
 * @author Alwyn
 * @date 2021-05-25 06:26
 */
@FunctionalInterface
public interface Converter<F, T> {

    T converter(F from);

    static Converter staticCover() {
        return null;
    }

}
