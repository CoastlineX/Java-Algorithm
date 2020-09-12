package com.kalton.data_structure.list;

/**
 * TODO
 * 抽象父类
 * @author 衍方
 * @date 2020/8/31 - 22:52
 */
public abstract class AbstractList<E> implements List<E>  {
    /**
     * 元素的数量
     */
    protected int size;

    /**
     * 元素的数量
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到尾部
     * @param element
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 非法索引访问数组异常
     * @param index
     */
    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    /**
     * 索引检查函数
     * @param index
     */
    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    /**
     * 数组添加元素的索引检查函数
     * @param index
     */
    protected void rangeCheckForAdd(int index) {
        //index > size,元素可以添加在数组size位置，即数组尾部下一存储单元
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}

