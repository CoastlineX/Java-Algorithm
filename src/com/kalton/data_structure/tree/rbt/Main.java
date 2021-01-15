package com.kalton.data_structure.tree.rbt;

/**
 * 红黑树 —— 测试类
 *
 * @author 衍方
 * @date 2021/1/14 - 17:14
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class Main {

    // 数据源
    static Integer[] data = new Integer[] {
            55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
    };

    // 添加测试
    static void test1(){

        RedBlackTree<Integer> rb = new RedBlackTree<>();
        for (Integer value : data) {
            rb.add(value);
        }

        System.out.println("------层序遍历------");
        rb.levelOrder();
        System.out.println(rb);
    }

    // 删除测试
    static void test2(){

        RedBlackTree<Integer> rb = new RedBlackTree<>();
        for (Integer value : data) {
            rb.add(value);
        }

        System.out.println(rb);

        for (Integer value : data) {
            rb.remove(value);
            System.out.println("[" + value + "]");
            System.out.println(rb);
            System.out.println("--------------------");
        }
    }

    // 历史对比删除测试
    static void test3(){

        RedBlackTree<Integer> rb = new RedBlackTree<>();
        RedBlackTree_History<Integer> rbh = new RedBlackTree_History<>();
        for (Integer value : data) {
            rb.add(value);
            rbh.add(value);
        }

        for (Integer value : data) {
            rb.remove(value);
            rbh.remove(value);

            System.out.println("[" + value + "]");
            System.out.println("---------rb-----------");
            System.out.println(rb);
            System.out.println("----------------------");
            System.out.println(rbh);
            System.out.println("---------rbh----------");
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
