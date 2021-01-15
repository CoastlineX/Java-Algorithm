package com.kalton.data_structure.queue;


import com.kalton.data_structure.stack.Stack;

/**
 * 数据结构 -- 使用双栈实现队列
 *
 * @author 衍方
 * @date 2020/9/13 - 13:10
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class QueueByStack<E> {

    // 定义两个栈，inStack用于队尾入队，outStack用于队头出队
    private Stack<E> inStack,outStack;

    // 使用构造函数初始化
    public QueueByStack() {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
    }

    /**
     * 获取队列元素数量
     * @return
     */
    public int size() {
        return inStack.size() + outStack.size();
    }

    /**
     * 判断当前队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    /**
     * 入队，从队尾添加元素
     * @param element
     */
    public void enQueue(E element) {
        inStack.push(element);
    }

    /**
     * 出队，从队头添加元素
     * @return
     */
    public E deQueue() {
        checkOutStack();
        return outStack.pop();
    }

    /**
     * 获取队头元素
     * @return
     */
    public E front() {
        checkOutStack();
        return outStack.top();
    }

    /**
     * 清空栈元素
     */
    public void clear() {
        inStack.clear();
        outStack.clear();
    }

    /**
     * 检查outStack是否为空，如果不为空，等着出队
     * 如果为空，且inStack不为空，将inStack中的
     * 元素出栈，入栈到outStack，然后准备出队
     */
    private void checkOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
