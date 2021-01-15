package com.kalton.data_structure.tree.bbst;

import java.util.Comparator;

/**
 * 平衡二叉搜索树 -- 抽取公共方法
 * 继承二叉搜索树，作为AVL树与红黑树的基类
 *
 * @author 衍方
 * @date 2021/1/14 - 15:59
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class BalancedBinaryTree<E> extends BinarySearchTree<E> {

    /**
     * 无参构造
     */
    public BalancedBinaryTree() {
        this(null);
    }

    /**
     * 带参构造
     * @param comparator
     */
    public BalancedBinaryTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 左旋转 -- 以RR模型图编写
     * @param grand
     */
    protected void leftRotated(Node<E> grand){

        // 记录当前grand节点的右子树
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        // 将parent的左子树作为grand的右子树
        grand.right = child;
        // 将grand作为parent的左子树
        parent.left = grand;

        // 更新节点的父节点及高度
        afterRotated(grand,parent,child);
    }

    /**
     * 右旋转 -- 以LL模型图编写
     * @param grand
     */
    protected void rightRotated(Node<E> grand){

        // 记录当前grand节点的右子树
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        // 将parent的右子树作为grand的左子树
        grand.left = child;
        // 将grand作为parent的右子树
        parent.right = grand;

        // 更新节点的父节点及高度
        afterRotated(grand,parent,child);
    }

    /**
     * 更新节点的父节点及高度
     * @param grand
     * @param parent
     * @param child
     */
    protected void afterRotated(Node<E> grand,Node<E> parent,Node<E> child){

        // 更新parent节点的父节点
        parent.parent = grand.parent;
        // 让parent成为字树的根节点
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        }else if (grand.isRightChild()){
            grand.parent.right = parent;
        }else {
            // grand是root节点
            root = parent;
        }

        // 更新child节点的父节点
        if (child != null){
            child.parent = grand;
        }

        // 更新grand节点的父节点
        grand.parent = parent;

    }

    /**
     * 统一旋转，不区分LL,RR,LR,RL
     * @param r
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     */
    protected void unifyRotated(
            Node<E> r, // 子树的原根节点
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f) {
        // 让d成为这棵子树新的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        //b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }


        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }


        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;

    }
}
