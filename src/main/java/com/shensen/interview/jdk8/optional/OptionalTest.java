package com.shensen.interview.jdk8.optional;

import java.util.Optional;

/**
 * Optional解决NPE问题
 *
 * @author Alwyn
 * @date 2021-05-25 06:58
 */
public class OptionalTest {

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        if (zoo != null) {
            Dog dog = zoo.getDog();
            if (dog != null) {
                int age = dog.getAge();
                System.out.println(age);
            }
        }

        // Optional 解决
        Optional.ofNullable(zoo).map(o -> o.getDog()).map(d -> d.getAge()).ifPresent(age -> System.out.println(age));
    }
}

class Zoo {

    private Dog dog;

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Dog getDog() {
        return dog;
    }
}

class Dog {

    private int age;

    public int getAge() {
        return age;
    }
}
