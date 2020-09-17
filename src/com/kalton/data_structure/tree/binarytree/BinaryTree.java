package com.kalton.data_structure.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * TODO
 * 二叉树 —— 基类
 * @author 衍方
 * @desc
 * @date 2020/9/17 - 1:53
 */
public class BinaryTree<E> {
    //树节点的数量
    protected int size;

    //树的根结点
    protected Node<E> root;

    /**
     * 自定义节点类，用于维护二叉树
     * @param <E>
     */
    protected static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        /**
         * 构造函数，添加节点时，要指定元素
         * 父节点的，但不一定有左右子节点
         * @param element
         * @param parent
         */
        public Node(E element,Node<E> parent){
            this.element = element;
            this.parent = parent;
        }


        /**
         * 判断是否为叶子节点
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 判断是否左右子节点都具备
         * @return
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    /**
     * 提供外部使用的遍历器接口
     * @param <E>
     */
    public abstract static class Visitor<E>{

        //遍历停止遍历的标记
        boolean stop;

        /**
         * visit方法将节点元素传给调用者
         * @param element
         * @return 如果返回true，结束遍历
         */
        abstract boolean visit(E element);
    }

    /**
     * 获取元素节点的数量
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断树是否为空树
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空树的所有元素
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 计算树的高度
     * @return
     */
    public int height(){
        //递归法
        //return heightByRecursive(root);

        //迭代法
        return heightByIteration();
    }

    /**
     * (递归法)获取传入节点的高度
     * @param node
     * @return
     */
    private int heightByRecursive(Node<E> node){
        if (node == null) return 0;
        return 1 + Math.max(heightByRecursive(node.left),heightByRecursive(node.right));
    }

    /**
     * (迭代法)获取树的高度，层序遍历
     * @return
     */
    private int heightByIteration(){
        if (root == null) return 0;

        // 树的高度
        int height = 0;
        // 存储着每一层的元素数量
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            // 意味着即将要访问下一层
            if (levelSize == 0) {
                //每一层访问完后，下一层的节点个数是队列的size
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }


    /**
     * 获取传入节点的前驱节点
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（left.right.right.right....）
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    /**
     * 获取传入节点的后继节点
     * @param node
     * @return
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（right.left.left.left....）
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 判断是否为完全二叉树 —— (层序遍历)
     * @return
     */
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                //相当于node.left == null && node.right != null
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                //node.left == null && node.right == null
                //node.left ！= null && node.right == null
                // 后面遍历的节点都必须是叶子节点
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 判断是否为真二叉树 —— (层序遍历)
     * @return
     */
    public boolean isProper (){
        if (root == null) return false;

        // 存储着每一层的元素数量
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            // 意味着即将要访问下一层
            if (levelSize == 0) {
                //每一层访问完后，下一层的节点个数是队列的size
                levelSize = queue.size();
                if (levelSize % 2 != 0) return false;
            }
        }
        return true;
    }

    /**
     * 前序遍历 —— 如果用户没有传遍历规则，默认打印元素
     */
    public void preorder(){
        preorder(new Visitor<E>() {
            @Override
            boolean visit(E element) {
                System.out.print(element + " ");
                return false;
            }
        });
    }

    /**
     * 增强前序遍历，提供调用者编写自己的遍历规则
     * @param visitor
     */
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) return;

        /**
         * 底层使用递归法
         */
        //preorderByRecursive(root, visitor);

        /**
         * 底层使用迭代法
         */
        preorderByIteration(visitor);
        System.out.println();
    }

    /**
     * 递归法实现 —— 前序遍历
     * @param node
     * @param visitor
     */
    private void preorderByRecursive(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        visitor.stop = visitor.visit(node.element);
        preorderByRecursive(node.left, visitor);
        preorderByRecursive(node.right, visitor);
    }

    /**
     * 迭代法实现 —— 前序遍历
     * @param visitor
     */
    private void preorderByIteration(Visitor<E> visitor){
        if (root == null || visitor == null) return;

        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();

            //传给外部调用者,如果条件成立，停止遍历
            if (visitor.visit(node.element)) return;

            if (node.right != null){
                stack.push(node.right);
            }

            if (node.left != null){
                stack.push(node.left);
            }
        }
    }

    /**
     * 中序遍历 —— 如果用户没有传遍历规则，默认打印元素
     */
    public void inorder(){
        inorder(new Visitor<E>() {
            @Override
            boolean visit(E element) {
                System.out.print(element + " ");
                return false;
            }
        });
    }
    /**
     * 增强中序遍历，提供调用者编写自己的遍历规则
     * @param visitor
     */
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        /**
         * 底层使用递归法
         */
        //inorderByRecursive(root, visitor);

        /**
         * 底层使用迭代法
         */
        inorderByIteration(visitor);
        System.out.println();
    }

    /**
     * 递归法实现 —— 中序遍历
     * @param node
     * @param visitor
     */
    private void inorderByRecursive(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        inorderByRecursive(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderByRecursive(node.right, visitor);
    }

    /**
     * 迭代法实现 —— 中序遍历
     * @param visitor
     */
    private void inorderByIteration(Visitor<E> visitor){
        if (root == null || visitor == null) return;

        Stack<Node<E>> stack = new Stack<>();
        Node<E> node = root;
        while (node != null || !stack.isEmpty()) {

            while (node != null){
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            if (visitor.visit(node.element)) return;
            node = node.right;
        }
    }

    /**
     * 后序遍历 —— 如果用户没有传遍历规则，默认打印元素
     */
    public void postorder(){
        postorder(new Visitor<E>() {
            @Override
            boolean visit(E element) {
                System.out.print(element + " ");
                return false;
            }
        });
    }

    /**
     * 增强后序遍历，提供调用者编写自己的遍历规则
     * @param visitor
     */
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) return;
        /**
         * 底层使用递归法
         */
        //postorderByRecursive(root, visitor);

        /**
         * 底层使用迭代法
         */
        postorderByIteration(visitor);
        System.out.println();
    }

    /**
     * 递归法实现 —— 后序遍历
     * @param node
     * @param visitor
     */
    private void postorderByRecursive(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        postorderByRecursive(node.left, visitor);
        postorderByRecursive(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 迭代法实现 —— 后序遍历
     * @param visitor
     */
    private void postorderByIteration(Visitor<E> visitor){
        if (root == null || visitor == null) return;

        Node<E> node = root;
        Node<E> lastVisited = null;
        Stack<Node<E>> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {

            while (node != null){
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            //栈顶节点是叶子节点或者上一次访问的节点是栈顶节点的子节点
            if (node.right == null || node.right == lastVisited){
                if (visitor.visit(node.element)) return;
                lastVisited = node;
                //这里node没有改变指向，所以需要指向null，否则会死循环
                node = null;
            }else {
                //既不是子节点且上一次访问的节点又不是栈顶节点的子节点话，代表是父节点，重新进栈
                stack.push(node);
                node = node.right;
            }
        }
    }

    /**
     * 层序遍历 —— 如果用户没有传遍历规则，默认打印元素
     */
    public void levelOrder(){

        levelOrder(new Visitor<E>() {
            @Override
            boolean visit(E element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();
    }

    /**
     * 增强层序遍历，交由用户编写逻辑
     * @param visitor
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            //传给外部调用者,如果条件成立，停止遍历
            if (visitor.visit(node.element)) return;
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 树状打印二叉树
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root,sb,"");
        return sb.toString();
    }
    //中序遍历，树状打印二叉树
    private void toString(Node<E> node, StringBuilder sb, String prefix){
        if (node == null) return;

        toString(node.left,sb,prefix + "L->");
        sb.append(prefix).append(node.element).append("\n");
        toString(node.right,sb,prefix + "R->");
    }
}
