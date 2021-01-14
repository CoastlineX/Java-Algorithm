package com.kalton.data_structure.tree.rbt;

/**
 * 红黑树 —— 测试类
 *
 * @author 衍方
 * @date 2021/1/14 - 17:14
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class Main {

    static void test1(){

        Integer[] data = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RedBlackTree<Integer> rb = new RedBlackTree<>();
        for (Integer value : data) {
            rb.add(value);
        }

        System.out.println("------层序遍历------");
        rb.levelOrder();
        System.out.println(rb);
    }

    public static void main(String[] args) {
        test1();
    }
}
