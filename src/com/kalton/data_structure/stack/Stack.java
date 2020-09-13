package com.kalton.data_structure.stack;


import com.kalton.data_structure.dynamic_array.DynamicArray;
import com.kalton.data_structure.list.List;

public class Stack<E> extends DynamicArray<E> {

	//利用动态数组实现栈
	private List<E> list = new DynamicArray<>();

	//利用链表实现栈
	//private List<E> list = new DynamicArray<>();

	/**
     * 查看栈元素数量
	 * @return
     */
	public int size() {
		return list.size();
	}

	/**
     * 判断栈是否为空
	 * @return
     */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * 入栈，添加元素
	 * @param element
	 */
	public void push(E element){
		list.add(element);
	}

	/**
	 * 出栈，删除尾部元素
	 */
	public E pop(){
		return list.remove(list.size() - 1);
	}

	/**
	 * 获取栈顶元素
	 * @return
	 */
	public E top(){
		return list.get(list.size() - 1);
	}


    /**
     * 清空栈元素
	 */
	public void clear() {
		list.clear();
	}
}
