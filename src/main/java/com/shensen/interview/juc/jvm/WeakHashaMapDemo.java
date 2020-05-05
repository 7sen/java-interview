package com.shensen.interview.juc.jvm;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * <p>
 * 题目：你知道弱引用的话，能谈谈WeakHashMap吗？
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-19 08:36
 */
public class WeakHashaMapDemo {

    public static void main(String[] args) {
        showHashMap();
        System.out.println("----------------");
        showWeekHashMap();
    }

    private static void showWeekHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        map.put(key, "WeakHashMap");
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }

    private static void showHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        map.put(key, "HashMap");
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }

}
