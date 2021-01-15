package com.kalton.data_structure.tree.bbst;

import com.kalton.data_structure.tree.binarytree.BinaryTree;

import java.util.Comparator;

/**
 * 平衡二叉树基类 —— 二叉搜索树，copy，防止原代码修改，造成阅读干扰
 *
 * @author 衍方
 * @desc
 * @date 2020/9/15 - 15:15
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree<E> {

    //接收用户自定义比较器
    private final Comparator<E> comparator;

    /**
     * 无参构造
     */
    public BinarySearchTree() {
        this(null);
    }

    /**
     * 构造函数
     * @param comparator
     */
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    /**
     * 新节点元素非空检查
     * @param element
     * @return
     */
    private void elementNotNullCheck(E element){
        if (element == null){
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 比较函数，返回0，e1==e2;返回值大于0，e1>e2;返回小于0，e1<e2
     * @param e1
     * @param e2
     * @return
     */
    private int compare(E e1,E e2){
        if (comparator != null){
            return comparator.compare(e1,e2);
        }
        return ((Comparable)e1).compareTo(e2);
    }

    /**
     * 向二叉树添加节点
     * @param element
     */
    public void add(E element){
        elementNotNullCheck(element);

        //空树，添加第一个节点
        if (root == null){
            root = createNode(element,null);
            size++;

            // 新增节点后续处理
            afterAdd(root);
            return;
        }

        //非空树情况,找到其父节点
        Node<E> node = root;
        //记住找到的父节点，默认根结点
        Node<E> parent = root;
        //记住最后一次的比较情况
        int cmp = 0;
         while (node != null){
            cmp = compare(element,node.element);
            if (cmp > 0){
                parent = node;
                //大于父节点值，取右子节点比较
                node = node.right;
            }else if (cmp < 0){
                parent = node;
                //小于父节点值，取左子节点比较
                node = node.left;
            }else {
                //相等，第1种处理方法，不处理
                //return;
                //相等，第2种处理方法，覆盖原有节点
                node.element = element;
            }
         }

         //插入新节点
         Node<E> newNode = createNode(element,parent);
         if (cmp > 0){
            parent.right = newNode;
         }else {
             parent.left = newNode;
         }
         size++;

        // 新增节点后续处理
        afterAdd(newNode);

    }

    /**
     * 用于二叉平衡树节点添加导致失衡的后续操作
     *
     * @param node
     */
    protected void afterAdd(Node<E> node) {

    }

    /**
     * 删除元素为element的节点
     * @param element
     */
    public void remove(E element) {
        remove(node(element));
    }

    /**
     * 删除传入的节点
     * @param node
     */
    private void remove(Node<E> node) {
        if (node == null) return;

        size--;
        // 删除度为2的节点，实际上是转化为删除度为1或者0的节点
        if (node.hasTwoChildren()) {
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        } 

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;
        // node是度为1的节点
        if (replacement != null) {
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 平衡二叉树删除节点后续操作
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;

            // 平衡二叉树删除节点后续操作
            afterRemove(node);
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 平衡二叉树删除节点后续操作
            afterRemove(node);
        }
    }

    /**
     * 用于二叉平衡树节点删除导致失衡的后续操作
     *
     * @param node 被删除的节点，或者用以取代删除节点(度为1)的子节点
     */
    protected void afterRemove(Node<E> node) {

    }

    /**
     * 获取元素为element的节点
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else {
                // cmp < 0
                node = node.left;
            }
        }
        return null;
    }

    /**
     * 判断树是否包含值为element的节点
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return node(element) != null;
    }

}

