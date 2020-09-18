package com.kalton.data_structure.tree.bst;

import com.kalton.data_structure.tree.binarytree.BinaryTree;

/**
 * TODO
 * 二叉搜索树 —— 测试类
 * @author 衍方
 * @desc
 * @date 2020/9/18 - 23:06
 */
public class Main {


    static void test1(){
        //数据源
        int[] data = {7,4,9,2,5,8,11,3,12,1};
        BinarySearchTree<Integer> bSTree = new BinarySearchTree<>();
        //测试添加
        for (int i:data) {
            bSTree.add(i);
        }
        System.out.println("树的高度 ——>" + bSTree.height());
        System.out.println(bSTree);
        //测试删除
        bSTree.remove(1);
        bSTree.remove(7);
        System.out.println(bSTree);
        //测试是否包含某个元素
        if (bSTree.contains(8)){
            System.out.println("确有此人");
        }
        //测试是否完全二叉树
        if (bSTree.isComplete()){
            System.out.println("是棵完全二叉数");
        }else {
            System.out.println("有点不完全哦");
        }
        //测试是否完全二叉树
        if (bSTree.isProper()){
            System.out.println("是棵真二叉数");
        }else {
            System.out.println("有点假哦");
        }
    }

    //测试树的遍历
    static void test2(){
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 1 ,5, 8, 3

        };

        BinarySearchTree<Integer> bSTree = new BinarySearchTree<>();
        for (Integer datum : data) {
            bSTree.add(datum);
        }
        System.out.println("------前序遍历------");
        bSTree.preorder();
        bSTree.preorder(new BinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " | ");
                return element == 2;
            }
        });
        System.out.println("------------------");

        System.out.println("------中序遍历------");
        bSTree.inorder();
        bSTree.inorder(new BinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " | ");
                return element == 2;
            }
        });
        System.out.println("------------------");

        System.out.println("------后序遍历------");
        bSTree.postorder();
        bSTree.postorder(new BinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " | ");
                return element == 2;
            }
        });
        System.out.println("------------------");

        System.out.println("------层序遍历------");
        bSTree.levelOrder();
        bSTree.levelOrder(new BinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " | ");
                return element == 2;
            }
        });
        System.out.println("\n" + "------------------");
        System.out.println("------树状打印------");
        System.out.println(bSTree);
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    //测试类 Person
    public class person implements Comparable<person>{
        /**
         * 年龄
         */
        private int age;

        public person(int age){
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        //自定义比较规则
        @Override
        public int compareTo(person p) {
            return Integer.compare(age, p.age);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    '}';
        }
    }
}
