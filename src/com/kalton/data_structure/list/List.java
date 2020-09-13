package com.kalton.data_structure.list;

/**
 * TODO
 * 公共方法接口
 * @author 衍方
 * @date 2020/8/31 - 22:54
 */
public interface List<E> {

    //查无元素的返回标志
    int ELEMENT_NOT_FOUND = -1;

    /**
     * 元素的数量
     * @return
     */
    int size();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 设置index位置的元素
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    E set(int index, E element);

    /**
     * 获取index位置的元素
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    int indexOf(E element);

    /**
     * 添加元素到尾部
     * @param element
     */
    void add(E element);

    /**
     * 在index位置插入一个元素
     * @param index
     * @param element
     */
    void add(int index, E element);

    /**
     * 删除index位置的元素
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 清除所有元素
     */
    void clear();
}

