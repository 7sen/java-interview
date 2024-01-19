package com.shensen.interview.algorithm.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 题：给定一个区间的集合 intervals ，其中 intervals[i] = [start, end] 。返回 需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * <pre>
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 示例 2:
 *
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * </pre>
 */
class EraseOverlapIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (null == intervals || intervals.length == 0) return 0;
        int num = intervals.length;

        // 按end升序排列
        Arrays.asList(intervals).sort(Comparator.comparingInt(a -> a[1]));

        // 1.最多有几个互不相交区间
        // ⾄少有⼀个区间不相交
        int count = 1;
        // 排序后，第⼀个区间就是 x
        int x_end = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start >= x_end) {
                // 找到下⼀个选择的区间了
                ++count;
                x_end = interval[1];
            }
        }

        // 2.长度-至多互不相交区间
        return num - count;
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("输入: intervals = [[1,2],[2,3],[3,4],[1,3]]");
        System.out.println("输出: " + new EraseOverlapIntervals().eraseOverlapIntervals(intervals1));

        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println("输入: intervals = [[1,2],[1,2],[1,2]]");
        System.out.println("输出: " + new EraseOverlapIntervals().eraseOverlapIntervals(intervals2));
    }
}
