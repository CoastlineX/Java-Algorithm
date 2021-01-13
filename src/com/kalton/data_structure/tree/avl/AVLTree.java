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
        int height = 1;

        /**
         * 构造函数，添加节点时，要指定元素
         * 的父节点，但不一定有左右子节点
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

        /**
         * 更新节点的高度
         */
        public void updateHeight(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight,rightHeight);
        }

        /**
         * 查找子节点中高度较高的子节点
         * @return
         */
        public Node<E> tallerChild(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;

            if (leftHeight > rightHeight){
                return left;
            }else if (leftHeight < rightHeight){
                return right;
            }else {
                // 高度相等时，返回与父节点同方向的节点
                return isLeftChild() ? left : right;
            }
        }
    }

    /**
     * 更新节点的高度
     */
    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }



    /**
     * 判断节点是否失衡
     * @param node
     * @return
     */
    private boolean isBalanced(Node<E> node){

        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;

    }

    /**
     * 恢复平衡,grand是高度最低的失衡点
     * @param grand
     */
    private void reBalance(Node<E> grand){

        // parent是grand节点的左右节点中高度较高的那个
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();

        // node是parent节点的左右节点中高度较高的那个
        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        // 判断是LL,RR,LR,RL四种失衡模型
        if (parent.isLeftChild()){

            if (node.isLeftChild()){      // LL
                rightRotated(grand);
            }else {      // LR
                leftRotated(parent);
                rightRotated(grand);
            }
        }else {

            if (node.isLeftChild()){      // RL
                rightRotated(parent);
                leftRotated(grand);
            }else {      // RR
                leftRotated(grand);
            }
        }
    }

    /**
     * 左旋转 -- 以RR模型图编写
     * @param grand
     */
    private void leftRotated(Node<E> grand){

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
    private void rightRotated(Node<E> grand){

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
     * 统一操作，恢复平衡,grand是高度最低的失衡点
     * @param grand
     */
    private void unifyReBalance(Node<E> grand){

        // parent是grand节点的左右节点中高度较高的那个
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();

        // node是parent节点的左右节点中高度较高的那个
        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        // 判断是LL,RR,LR,RL四种失衡模型，传参
        if (parent.isLeftChild()){

            if (node.isLeftChild()){      // LL
                unifyRotated(grand, node, node.right, parent, parent.right, grand);
            }else {      // LR
                unifyRotated(grand, parent, node.left, node, node.right, grand);
            }
        }else {

            if (node.isLeftChild()){      // RL
                unifyRotated(grand, grand, node.left, node, node.right, parent);
            }else {      // RR
                unifyRotated(grand, grand, parent.left, parent, node.left, node);
            }
        }
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
    private void unifyRotated(
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
        updateHeight(b);

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }

    /**
     * 更新节点的父节点及高度
     * @param grand
     * @param parent
     * @param child
     */
    private void afterRotated(Node<E> grand,Node<E> parent,Node<E> child){

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

        // 自底向上更新节点高度
        updateHeight(grand);
        updateHeight(parent);
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
     * 添加节点导致的失衡处理
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null){
            if (isBalanced(node)){
                // 更新高度
                updateHeight(node);
            }else {
                // 恢复平衡,node是高度最低的失衡点
                reBalance(node);
                break;
            }
        }
    }

    /**
     * 删除节点导致的失衡处理
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null){
            if (isBalanced(node)){
                // 更新高度
                updateHeight(node);
            }else {
                // 恢复平衡,node是高度最低的失衡点
                reBalance(node);
            }
        }
    }

    /**
     * 树状打印AVL树
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
        sb.append(prefix).append(node.element).append("<H").append(((AVLNode<E>) node).height).append(">").append("\n");
        toString(node.right,sb,prefix + "R->");
    }
}
