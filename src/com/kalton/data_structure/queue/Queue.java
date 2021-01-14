package com.kalton.data_structure.queue;

import com.kalton.data_structure.linkedlist.Both_LinkedList;
import com.kalton.data_structure.list.List;

/**
 * 数据结构 - 队列
 *
 * @author 衍方
 * @date 2020/9/13 - 12:05
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class Queue<E> {

	// 利用双向链表封装好的方法实现队列
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
	 * 入队，从队尾添加元素
	 * @param element
	 */
	public void enQueue(E element) {
		list.add(element);
	}

	/**
	 * 出队，从队头移除元素
	 * @return
	 */
	public E deQueue() {
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
	 * 清空队列元素
	 */
	public void clear() {
		list.clear();
	}
}
