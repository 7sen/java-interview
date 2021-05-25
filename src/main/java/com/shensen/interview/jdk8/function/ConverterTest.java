package com.shensen.interview.jdk8.function;

public class ConverterTest {

    public static void main(String[] args) {
        Converter<String, Integer> converter = from -> Integer.valueOf(from);
        System.out.println(converter.converter("1"));
    }
}
