package com.shensen.interview.algorithm;

/**
 * <p>
 * 算法题：
 * ["ACB","AGYSRB","ABGHJUOIP...",...]
 * 有一个字符串数组，将数组中的每一个字符串按照字母序排序后，再将整个字符串数组按照字典序排序。
 * 整个操作的时间复杂度为多少?
 *
 * 解题思路：设数组长度为n，字符串的长度最长的为s。则一个字符串按字母序排序为O(slogs)，
 * 一共为O(nslogs)，再将整个数组按字典序排序为**O(snlogn)**。得出总的时间复杂度为：
 * O(n*slogs) + O(s*nlogn) = O(n*slogs + s*nlogn) = O( n*s*(logs+logn) )
 *
 * 算法题：
 * S1="ABCDFUIOPUY..."
 * S2="ADF"
 *
 * 判断S2是否为S1子序列(S2、S1顺序一致，S2是S1子级)
 * </p>
 *
 * @author Alwyn
 * @date 2019-07-08 13:35
 */
public class GuaZiAlg {

    public static void main(String[] args) {
        String s1 = "ABCDFGIOPUY";
        String s2 = "ABCU1";
        boolean flag = isSubsequence(s1, s2);
        System.out.println(flag);
    }

    private static boolean isSubsequence1(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int index = 0, count = 0;
        boolean flag = false;
        Lable:
        for (int i = 0; i < arr2.length; i++) {
            for (int j = index; j < arr1.length; j++) {
                count++;
                if (arr2[i] == arr1[j]) {
                    if (index - 1 > j) {
                        flag = false;
                        break Lable;
                    }
                    index = j + 1;
                    break;
                }

                // 字符串不存在
                if (count == arr1.length - index) {
                    flag = false;
                    break Lable;
                }

                // 跳出循环
                if (i == arr2.length - 1) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private static boolean isSubsequence(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int i = 0, j = 0, index = 0;
        boolean flag = false;
        while (i < s1.length() && j < s2.length()) {
            if (arr2[j] == arr1[i]) {
                if (index > i) {
                    flag = false;
                    break;
                }
                index = i;
                j++;
            }
            i++;
            if (j == s2.length()) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
