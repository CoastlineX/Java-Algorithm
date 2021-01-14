package com.kalton.data_structure.tree.avl;

import com.kalton.data_structure.tree.bst.BinarySearchTree;

/**
 * AVL树 —— 测试类
 *
 * @author 衍方
 * @date 2021/1/4 - 11:05
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class Main {

    //数据源
    static int[] data = {85,19,69,3,7,99,95,2,1,70,44,58,11,21,14,93,57,4,56};
    //static int[] data = {67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39};

    // 未平衡的二叉搜索树
    static void test1(){

        BinarySearchTree<Integer> bSTree = new BinarySearchTree<>();
        //测试添加
        for (int i:data) {
            bSTree.add(i);
        }
        System.out.println("树的高度 ——>" + bSTree.height());
        System.out.println(bSTree);

        System.out.println("------层序遍历------");
        bSTree.levelOrder();
    }

    // 平衡的AVL树
    static void test2(){

        AVLTree<Integer> avlTree = new AVLTree<>();
        //测试添加
        for (int i:data) {
            avlTree.add(i);
        }
        System.out.println("树的高度 ——>" + avlTree.height());
        System.out.println(avlTree);

        System.out.println("------层序遍历------");
        avlTree.levelOrder();

    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
