package com.kalton.data_structure.tree.rbt;

import com.kalton.data_structure.tree.bbst.BalancedBinaryTree;

import java.util.Comparator;

/**
 * 平衡二叉树 —— 红黑树
 *
 * @author 衍方
 * @date 2021/1/13 - 10:56
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class RedBlackTree<E> extends BalancedBinaryTree<E> {

    // 定义红黑树节点颜色常量
    private static final boolean RED = false;
    private static final boolean BLACK = true;

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

        /**
         * 重写红黑树节点显示
         * @return
         */
        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }

    /**
     * 重写创建节点方法，创建红黑树节点
     * @param element
     * @param parent
     * @return
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
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
        // 按照 红黑红，红黑，黑红，黑;四种模型共有12种添加情况
        Node<E> parent = node.parent;

        // 没有父节点，添加的是root或上溢到根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 父节点为black,直接添加，不造成影响
        if (isBlack(parent)) return;

        // 父节点为red,8种情况，分两种类型
        Node<E> uncle = node.uncle();
        // 先将祖父节点染红
        Node<E> grand = red(parent.parent);
        // 1、叔父节点为red,发生上溢
        if (isRed(uncle)){
            // 父节点与叔父节点染黑
            black(parent);
            black(uncle);
            // 祖父节点染红，当做新节点添加
            afterAdd(grand);
            return;
        }

        // 2、叔父节点为black,需要旋转
        // 共有LL,RR,LR,RL四种失衡模型
        if (parent.isLeftChild()){
            if (node.isLeftChild()){      // LL
                // 将父节点染黑，祖父节点染红
                black(parent);
            }else {      // LR
                // 将自己染黑，祖父节点染红
                black(node);
                leftRotated(parent);
            }
            rightRotated(grand);
        }else {
            if (node.isLeftChild()){      // RL
                // 将自己染黑，祖父节点染红
                black(node);
                rightRotated(parent);
            }else {      // RR
                // 将父节点染黑，祖父节点染红
                black(parent);
            }
            leftRotated(grand);
        }

    }

    /**
     * 删除节点导致的失衡处理
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node) {

        /*
         * 真正被删除的元素都是叶子节点
         * 1、删除的节点是红色，直接删除
         * 2、删除的节点是黑色，分三种，红黑红，黑红-红黑，黑
         * 2.1、红黑红，采取前驱或后继节点取代删除，不做处理
         * 2.2、黑红-红黑，直接用红色子节点取代，然后染黑
         * 2.3、复杂模型，见下面删除代码注释
         */

        // 如果删除的节点是红色
        // 或者用以取代删除节点的子节点是红色
        if (isRed(node)){
            black(node);
            return;
        }

        // 删除的是黑色叶子节点
        Node<E> parent = node.parent;
        // 1、删除的是根节点
        if (parent == null) return;
        // 2、非根节点，发生下溢
        /*
         * 操作 —— 获取兄弟节点
         * 错误，node删除后，左右子节点的线断开了
         * Node<E> sibling = node.sibling();
         */
        // 判断被删除的node节点是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;

        /* 2.1、兄弟节点(black) —— 以删除节点为右子节点分析
         * 2.1.1、存在红色子节点可借
         *
         * 这种情况分为黑红(LR)，红黑(LL)，红黑红(LL)三种
         * LR先左旋再右旋，红黑红采用LL右旋
         * 旋转后中心节点需要继承parent颜色，左右节点染黑
         *
         * 2.1.2、不存在红色子节点可借
         *
         * 下溢，parent向下合并兄弟节点
         * 将父节点染黑，兄弟节点染红
         * 如果parent为黑色，会再次下溢，将parent当成删除节点处理，递归
         *
         * 2.2、兄弟节点(red)
         * 2.2.1不存在红色子节点可借
         *
         * 将兄弟节点的子节点(侄子)变成兄弟
         * 将兄弟节点染黑，父节点染红
         * LL模型，需要对parent进行右旋转即可
         * 回到2.1操作
         */
        if (left){  // 删除的节点在左边，兄弟节点在右边

            // 兄弟节点(red)
            if (isRed(sibling)){
                black(sibling);
                red(parent);
                leftRotated(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点(black)
            if (isBlack(sibling.left) && isBlack(sibling.right)){

                // 不存在红色子节点可借
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                // 判断父节点是否为黑色
                if (parentBlack){
                    afterRemove(parent);
                }

            }else {

                // 存在一个至少红色子节点可借
                // 先对黑红进行左旋转，再统一左旋转
                if (isBlack(sibling.right)){
                    rightRotated(sibling);
                    // 更换兄弟
                    sibling = parent.right;
                }
                // 中心节点需要继承parent颜色
                color(sibling,colorOf(parent));
                black(sibling.right);
                black(parent);
                // 统一左旋转
                leftRotated(parent);
            }

        }else {     // 删除的节点在右边，兄弟节点在左边

            // 兄弟节点(red)
            if (isRed(sibling)){
                black(sibling);
                red(parent);
                rightRotated(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点(black)
            if (isBlack(sibling.left) && isBlack(sibling.right)){

                // 不存在红色子节点可借
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                // 判断父节点是否为黑色
                if (parentBlack){
                    afterRemove(parent);
                }

            }else {

                // 存在一个至少红色子节点可借
                // 先对黑红进行左旋转，再统一右旋转
                if (isBlack(sibling.left)){
                    leftRotated(sibling);
                    // 更换兄弟
                    sibling = parent.left;
                }
                // 中心节点需要继承parent颜色
                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                // 统一右旋转
                rightRotated(parent);
            }
        }
    }

    /**
     * 树状打印红黑树
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString((RBNode<E>) root,sb,"");
        return sb.toString();
    }

    // 中序遍历，树状打印二叉树
    private void toString(RBNode<E> node, StringBuilder sb, String prefix){
        if (node == null) return;

        toString((RBNode<E>) node.left,sb,prefix + "L->");
        if (node.color == RED) {
            sb.append(prefix).append("红").append(node.element).append("\n");
        }else {
            sb.append(prefix).append(node.element).append("\n");
        }
        toString((RBNode<E>) node.right,sb,prefix + "R->");
    }

}
