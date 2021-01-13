package com.kalton.data_structure.tree.rbt;

import java.util.Comparator;

/**
 *
 * 平衡二叉树 —— AVL树
 * @author 衍方
 * @date 2021/1/13 - 10:56
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class RedBlackTree<E> extends BinarySearchTree<E> {

    // 定义红黑树节点颜色常量
    private static final boolean RED = false;
    private static final boolean BLACK = false;

    /**
     * 无参构造
     */
    public RedBlackTree() {
        this(null);
    }

    /**
     * 带参构造
     * @param comparator
     */
    public RedBlackTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * AVL树结点类，增加height属性，维护平衡
     * 不写在基类BinaryTree，减少空间开销
     * @param <E>
     */
    public static class RBNode<E> extends Node<E>{

        // 定义节点颜色
        boolean color = RED  ;

        /**
         * 构造函数，添加节点时，要指定元素
         * 的父节点，但不一定有左右子节点
         *
         * @param element
         * @param parent
         */
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }

    /**
     * 染色操作
     * @param node
     * @param color
     */
    private Node<E> color(Node<E> node, boolean color){

        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    /**
     * 染红操作
     * @param node
     * @return
     */
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }

    /**
     * 染黑操作
     * @param node
     * @return
     */
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    /**
     * 查看节点颜色
     * @param node
     * @return
     */
    private boolean colorOf(Node<E> node){
        // 如果节点为空，返回黑色
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    /**
     * 判断节点是否为红色
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    /**
     * 判断节点是否为黑色
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }

    /**
     * 添加节点导致的失衡处理
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
    }

    /**
     * 删除节点导致的失衡处理
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }
}
