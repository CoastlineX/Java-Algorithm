package com.kalton.data_structure.tree.avl;
import java.util.Comparator;

/**
 *
 * 平衡二叉树 —— AVL树
 * @author 衍方
 * @date 2021/1/4 - 11:05
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class AVLTree<E> extends BinarySearchTree<E> {


    /**
     * 无参构造
     */
    public AVLTree() {
        this(null);
    }

    /**
     * 带参构造
     * @param comparator
     */
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * AVL树结点类，增加height属性，维护平衡
     * 不写在基类BinaryTree，减少空间开销
     * @param <E>
     */
    public static class AVLNode<E> extends Node<E>{

        // 存储结点高度，维护平衡因子
        int height;

        /**
         * 构造函数，添加节点时，要指定元素
         * 父节点的，但不一定有左右子节点
         *
         * @param element
         * @param parent
         */
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }


        /**
         * 获取节点类的平衡因子
         * @return
         */
        public int balanceFactor(){

            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }
    }

    /**
     * 判断节点是否失衡
     * @param node
     * @return
     */
    public boolean isBalanced(Node<E> node){

        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;

    }

    /**
     * 重写创建节点方法，创建AVLNode节点
     * @param element
     * @param parent
     * @return
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element,parent);
    }



    /**
     * 添加节点失衡处理
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
    }
}
