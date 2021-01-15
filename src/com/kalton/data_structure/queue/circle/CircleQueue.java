package com.kalton.data_structure.queue.circle;

/**
 * 数据结构 -- 循环队列
 *
 * @author 衍方
 * @date 2020/9/13 - 15:50
 * @link https://github.com/kaltons/Java-Algorithm
 */
@SuppressWarnings("unchecked")
public class CircleQueue<E> {

    // 数组的默认初始化值
    private static final int DEFAULT_CAPACITY = 10;

    // 循环队列队头指针
    private int front;

    // 队列元素数量
    private int size;

    // 使用顺序结构数组存储
    private E[] elements;

    /**
     * 构造函数初始化数组
     */
    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * 获取队列元素的数量
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 入队，从队尾添加元素
     * @param element
     */
    public void enQueue(E element) {
        ensureCapacity(size + 1);
        // elements[(front + size) % elements.length] = element;

        // 调用封装函数
        elements[index(size)] = element;
        size++;
    }

    /**
     * 出队，从队头移除元素
     * @return
     */
    public E deQueue() {
        E element = elements[front];
        elements[front] = null;
        // front = (front + 1) % elements.length;
        // 调用封装函数
        front = index(1);
        size--;
        return element;
    }


    /**
     * 获取队头元素
     * @return
     */
    public E front(){
        return elements[front];
    }

    /**
     * 清空队列元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            // elements[(i + front) % elements.length] = null;

            // 调用封装函数
            elements[index(i)] = null;
        }
        front = 0;
        size = 0;
    }

    /**
     * 保证要有capacity的容量,不足则扩容
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            // newElements[i] = elements[(i + front) % elements.length];

            // 调用封装函数
            newElements[i] = elements[index(i)];
        }
        elements = newElements;

        // 重置front
        front = 0;
    }

    /**
     * 索引映射函数，返回真实数组下标
     * @param index
     * @return
     */
    private int index(int index){
        // return (front + index) % elements.length;

        // 优化模运算，因为index不会超过2倍elements.length，可以转成减法运算
        // 已知n>=0，m>0，n%m等价于n–(m > n ? 0 : m)的前提条件：n < 2m
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capcacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}
