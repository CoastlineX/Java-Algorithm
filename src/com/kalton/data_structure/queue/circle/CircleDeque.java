package com.kalton.data_structure.queue.circle;

/**
 * TODO
 *
 * @author 衍方
 * @desc
 * @date 2020/9/13 - 15:50
 */
@SuppressWarnings("unchecked")
public class CircleDeque<E> {

    //数组的默认初始化值
    private static final int DEFAULT_CAPACITY = 10;

    //循环队列队头指针
    private int front;

    //队列元素数量
    private int size;

    //使用顺序结构数组存储
    private E[] elements;

    /**
     * 构造函数初始化数组
     */
    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * 获取队列元素数量
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断当前队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队，从队尾入队
     * @param element
     */
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    /**
     * 出队，从队尾出队
     * @return
     */
    public E deQueueRear() {
        //找到尾部元素的真实索引
        int last = index(size - 1);
        E element = elements[last];
        elements[last] = null;
        size--;
        return element;
    }

    /**
     * 入队，从队头入队
     * @param element
     */
    public void enQueueFront(E element) {

        //front指向当前节点前一位置
        front = index(-1);
        //假设虚拟索引，以front指向的位置为0，则向队头添加元素时往-1添加
        elements[front] = element;
        size++;
    }

    /**
     * 出队，从队头出队
     * @return
     */
    public E deQueueFront() {
        E element = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return element;
    }

    /**
     * 获取队头元素
     * @return
     */
    public E front() {
        return elements[front];
    }

    /**
     * 获取队尾元素
     * @return
     */
    public E rear() {
        return elements[index(size - 1)];
    }

    /**
     * 清空队列元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            //elements[(i + front) % elements.length] = null;

            //调用封装函数
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
        index += front;

        //但真实index为0时，往队头添加元素，传入 -1，小于0
        if (index < 0){
            index += elements.length;
        }
        //return index % elements.length;

        //优化模运算，因为index不会超过2倍elements.length，可以转成减法运算
        //已知n>=0，m>0，n%m等价于n–(m > n ? 0 : m)的前提条件：n < 2m
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
