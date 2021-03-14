package com.shensen.interview.algorithm.leetcode;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 题目：两数之和
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * </p>
 * <pre>
 *  示例：
 *     输入：nums = [2,7,11,15], target = 9
 *     输出：[0,1]
 *     解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * </pre>
 *
 * @author Alwyn
 * @date 2021-02-27 14:06
 */
public class TwoSum {

    /**
     * 方法一：暴力破解
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N^2)，其中 N 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     * 空间复杂度：O(1).
     * </p>
     */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * 方法二：哈希表
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 是数组中的元素数量。对于每一个元素 x，我们可以 O(1) 地寻找 target - x。
     * 空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销。
     * </p>
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(JSON.toJSONString(twoSum1(nums, target)));
        System.out.println(JSON.toJSONString(twoSum2(nums, target)));
    }
}
