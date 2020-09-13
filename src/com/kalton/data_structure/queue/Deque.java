package com.kalton.data_structure.queue;


import com.kalton.data_structure.linkedlist.Both_LinkedList;
import com.kalton.data_structure.list.List;

/**
 * TODO
 * 双端队列
 * @author 衍方
 * @desc
 * @date 2020/9/13 - 14:59
 */
public class Deque<E> {

    //利用双向链表封装好的方法实现队列
    private List<E> list = new Both_LinkedList<>();

    /**
     * 获取队列元素数量
     * @return
     */
    public int size() {
        return list.size();
    }

    /**
     * 判断当前队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 入队，从队尾入队
     * @param element
     */
    public void enQueueRear(E element) {
        list.add(element);
    }

    /**
     * 出队，从队尾出队
     * @return
     */
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    /**
     * 入队，从队头入队
     * @param element
     */
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    /**
     * 出队，从对头出队
     * @return
     */
    public E deQueueFront() {
        return list.remove(0);
    }

    /**
     * 获取队头元素
     * @return
     */
    public E front() {
        return list.get(0);
    }

    /**
     * 获取队尾元素
     * @return
     */
    public E rear() {
        return list.get(list.size() - 1);
    }

    /**
     * 清空队列元素
     */
    public void clear() {
        list.clear();
    }
}
