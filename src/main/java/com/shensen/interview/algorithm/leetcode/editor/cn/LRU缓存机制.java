//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
//
// 
// 
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
// 
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 3000 
// 0 <= value <= 104 
// 最多调用 3 * 104 次 get 和 put 
// 
// Related Topics 设计 
// 👍 1252 👎 0


package com.shensen.interview.algorithm.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;

class LRUCache<K, V> {

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.get(1));
        cache.put(2, 3);
        System.out.println(cache.get(2));
    }

    private int cacheSize;
    private Map<K, Node<K, V>> map;
    private DoubleLinkedList<K, V> doubleLinkedList;

    LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public Node<K, V> get(int key) {
        if (!map.containsKey(key)) return null;
        Node<K, V> node = map.get(key);

        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);

        return map.get(key);
    }

    public void put(K key, V value) {
        // 包含key,移除
        Node<K, V> node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.val = value;

            doubleLinkedList.removeNode(map.get(key));
        } else {
            if (map.size() == cacheSize) {
                node = doubleLinkedList.getLastNode();
                map.remove(node.key);
                doubleLinkedList.removeNode(node);
            }
            node = new Node<>(key, value);
        }

        map.put(key, node);
        doubleLinkedList.addHead(node);
    }


    // 构造一个node节点作为数据载体
    class Node<K, V> {

        K key;
        V val;
        Node<K, V> next, prev;

        public Node() {
        }

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            boolean flag = false;
            if (null != prev.key && null != prev.next) {
                sb.append("链表结构:[" + prev.key + "," + prev.val + "] ");
                flag = true;
            }
            sb.append(flag ? "[" : "链表结构:[" + key + "," + val + "]");
            if (null != next.key && null != next.next) {
                sb.append(" [" + next.key + "," + next.val + "]");
            }
            return sb.toString();
        }
    }

    // 构建一个虚拟的双向链表,,里面安放的就是我们的Node
    class DoubleLinkedList<K, V> {

        Node<K, V> head;
        Node<K, V> tail;

        public DoubleLinkedList() {
            this.head = new Node<>();
            this.tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        // 向链表头部添加节点
        public void addHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // 移除链表节点
        public void removeNode(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        // 获取链表最后一个节点
        public Node getLastNode() {
            return tail.prev;
        }
    }

}
/*
class LRUCache {

    // key 映射到 Node(key, val)
    private Map<Integer, Node> map;
    // node(k1, v1) <-> Node(k2, v2)...
    private DoubleList cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 利⽤ put ⽅法把该数据提前
        int val = map.get(key).val;
        put(key, val);
        return val;
    }

    public void put(int key, int value) {
        // 先把新节点 x 做出来
        Node n = new Node(key, value);
        if (map.containsKey(key)) {
            // 删除旧的节点
            cache.remove(map.get(key));
        } else {
            if (cache.size() == capacity) {
                // 删除链表最后一个数据
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            // 直接添加到头部
        }
        // 插入新节点
        cache.addFirst(n);
        map.put(key, n);
    }

    class Node {

        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    class DoubleList {

        private Node head, tail;
        private int size;

        // 在链表头部添加节点 x，时间 O(1)
        public void addFirst(Node node) {
            if (head == null) {
                head = tail = node;
            } else {
                Node n = head;
                n.prev = node;
                node.next = n;
                head = node;
            }
            size++;
        }

        // 删除链表中的 x 节点（x 一定存在）
        // 由于是双链表且给的是目标 Node 节点，时间 O(1)
        public void remove(Node node) {
            if (head == node && tail == node) {
                head = null;
                tail = null;
            } else if (tail == node) {
                node.prev.next = null;
                tail = node.prev;
            } else if (head == node) {
                node.next.prev = null;
                head = node.next;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }

        // 删除链表中最后一个节点，并返回该节点，时间 O(1)
        public Node removeLast() {
            Node node = tail;
            remove(tail);
            return node;
        }

        // 返回链表长度，时间 O(1)
        public int size() {
            return size;
        }
    }*/
