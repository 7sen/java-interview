package com.sen.interview.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 题目：查找一个字符串中出现频率最高的字符并输出.
 * </p>
 *
 * @author Leo
 * @date 2019-07-05 11:20
 */
public class LookupHighestFrequentStr {

    public static void main(String[] args) {
        String str = "ccccaabbbaabcccdegg";
        char[] arr = str.toCharArray();

        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (charMap.get(arr[i]) != null) {
                charMap.put(arr[i], charMap.get(arr[i]) + 1);
            } else {
                charMap.put(arr[i], 1);
            }
        }

        Integer max = charMap.get(arr[0]);
        Character maxStr = arr[0];
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxStr = entry.getKey();
            }
        }

        System.err.print("出现频率最高字符串：" + maxStr);
    }
}
