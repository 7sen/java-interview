package com.shensen.interview.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 题目：用Java实现跳表.
 *
 * SkipList的性质：
 * 1、由很多层结构组成，Level是通过一定的概率随机产生的。
 * 2、每一层都是一个有序的链表，默认是升序
 * 3、最底层(Level 1)的链表包含所有元素。
 * 4、如果一个元素出现在Level i 的链表中，则它在Level i 之下的链表也都会出现。
 * 5、每个节点包含两个指针，一个指向同一链表中的下一个元素，一个指向下面一层的元素。
 * </p>
 *
 * @author Alwyn
 * @date 2019-07-18 17:49
 */
public class SkipList<T> {

    public static void main(String[] args) {
        //测试随机数生成的结果对二取模，结果是否是接近于0.5
        //        Random r = new Random(47);
        //        int t = 1, a = 1;
        //        while (a < 10000000) {
        //            a++;
        //            if (r.nextInt() % 2 == 0)
        //                t++;
        //        }
        //        System.out.println(t * 1.0 / a);

        SkipList<String> list = new SkipList<>();
        list.put(1, "1");
        System.out.println(list);
        list.put(2, "2");
        System.out.println(list);
        list.put(3, "3");
        System.out.println(list);
        list.put(4, "4");
        System.out.println(list);
        list.put(5, "5");
        System.out.println(list);
        list.delete(3);
        list.delete(3);
        System.out.println(list);
        System.out.println("查找4：" + list.get(4));
    }

    /**
     * 跳跃表的节点的构成
     *
     * @param <E>
     */
    private static class SkipNode<E> {

        E val;//存储的数据
        int score;//跳跃表按照这个分数值进行从小到大排序。
        SkipNode<E> next, down;//next指针，指向下一层的指针

        SkipNode() {
        }

        SkipNode(E val, int score) {
            this.val = val;
            this.score = score;
        }
    }

    private static final int MAX_LEVEL = 1 << 6;

    //跳跃表数据结构
    private SkipNode<T> top;
    private int level = 0;
    //用于产生随机数的Random对象
    private Random random = new Random();

    public SkipList() {
        //创建默认初始高度的跳跃表
        this(4);
    }

    //跳跃表的初始化
    public SkipList(int level) {
        this.level = level;
        int i = level;
        SkipNode<T> temp = null;
        SkipNode<T> prev = null;
        while (i-- != 0) {
            temp = new SkipNode<T>(null, Integer.MIN_VALUE);
            temp.down = prev;
            prev = temp;
        }
        top = temp;//头节点
    }

    /**
     * 产生节点的高度。使用抛硬币
     *
     * @return
     */
    private int getRandomLevel() {
        int lev = 1;
        while (random.nextInt() % 2 == 0) {
            lev++;
        }
        return lev > MAX_LEVEL ? MAX_LEVEL : lev;
    }

    /**
     * 查找跳跃表中的一个值
     *
     * @param score
     * @return
     */
    public T get(int score) {
        SkipNode<T> t = top;
        while (t != null) {
            if (t.score == score)
                return t.val;
            if (t.next == null) {
                if (t.down != null) {
                    t = t.down;
                    continue;
                } else
                    return null;
            }
            if (t.next.score > score) {
                t = t.down;
            } else
                t = t.next;
        }
        return null;
    }

    public void put(int score, T val) {
        //1，找到需要插入的位置
        SkipNode<T> t = top, cur = null;//若cur不为空，表示当前score值的节点存在
        List<SkipNode<T>> path = new ArrayList<>();//记录每一层当前节点的前驱节点
        while (t != null) {
            if (t.score == score) {
                cur = t;
                break;//表示存在该值的点，表示需要更新该节点
            }
            if (t.next == null) {
                path.add(t);//需要向下查找，先记录该节点
                if (t.down != null) {
                    t = t.down;
                    continue;
                } else {
                    break;
                }
            }
            if (t.next.score > score) {
                path.add(t);//需要向下查找，先记录该节点
                if (t.down == null) {
                    break;
                }
                t = t.down;
            } else
                t = t.next;
        }
        if (cur != null) {
            while (cur != null) {
                cur.val = val;
                cur = cur.down;
            }
        } else {//当前表中不存在score值的节点，需要从下到上插入
            int lev = getRandomLevel();
            if (lev > level) {//需要更新top这一列的节点数量，同时需要在path中增加这些新的首节点
                SkipNode<T> temp = null;
                SkipNode<T> prev = top;//前驱节点现在是top了
                while (level++ != lev) {
                    temp = new SkipNode<T>(null, Integer.MIN_VALUE);
                    path.add(0, temp);//加到path的首部
                    temp.down = prev;
                    prev = temp;
                }
                top = temp;//头节点
                level = lev;//level长度增加到新的长度
            }
            // 从后向前遍历path中的每一个节点，在其后面增加一个新的节点
            SkipNode<T> downTemp = null, temp = null, prev = null;
            // System.out.println("当前深度为"+level+",当前path长度为"+path.size());
            for (int i = level - 1; i >= level - lev; i--) {
                temp = new SkipNode<T>(val, score);
                prev = path.get(i);
                temp.next = prev.next;
                prev.next = temp;
                temp.down = downTemp;
                downTemp = temp;
            }
        }
    }

    /**
     * 根据score的值来删除节点。
     *
     * @param score
     */
    public void delete(int score) {
        //1,查找到节点列的第一个节点的前驱
        SkipNode<T> t = top;
        while (t != null) {
            if (t.next == null) {
                t = t.down;
                continue;
            }
            if (t.next.score == score) {
                // 在这里说明找到了该删除的节点
                t.next = t.next.next;
                t = t.down;
                //删除当前节点后，还需要继续查找之后需要删除的节点
                continue;
            }
            if (t.next.score > score)
                t = t.down;
            else
                t = t.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SkipNode<T> t = top, next = null;
        while (t != null) {
            next = t;
            while (next != null) {
                sb.append(next.score + " ");
                next = next.next;
            }
            sb.append("\n");
            t = t.down;
        }
        return sb.toString();
    }
}