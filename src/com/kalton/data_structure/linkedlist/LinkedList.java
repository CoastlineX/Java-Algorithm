package com.kalton.data_structure.linkedlist;

import com.kalton.data_structure.list.AbstractList;

/**
 * 数据结构 - 单向链表
 *
 * @author 衍方
 * @date 2020/8/31 - 22:14
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class LinkedList<E> extends AbstractList<E> {

    /**
     * 定义链表第一结点，指向链表头一个元素
     */
    private Node<E> first;

    /**
     * 定义结点类Node，包含元素和指向下一个结点的地址引用
     * @param <E>
     */
    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        // 如果元素为空
        if (element == null){
            Node<E> node = first;
            for (int i = 0;i < size;i++){
                if (node.element == null) return i;
                node = node.next;
            }
        }else {
            // 元素不为空
            Node<E> node = first;
            for (int i = 0;i < size;i++){
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        // 查无此元素
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == 0){
            first = new Node<>(element,first);
        }else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element,prev.next);
        }
        size++;
    }

    /**
     * 删除index位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node =first;
        if (index == 0){
            first = first.next;
        }else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    /**
     * 删除指定元素
     *
     * @param element
     * @return
     */
    public E remove(E element) {
        return remove(indexOf(element));
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        size = 0;
        first =null;
    }

    /**
     * 获取index位置对应的结点对象
     * @param index
     * @return
     */
    private Node<E> node(int index){
        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(node.element);

            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
