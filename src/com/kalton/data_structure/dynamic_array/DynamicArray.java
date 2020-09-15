package com.kalton.data_structure.dynamic_array;


import com.kalton.data_structure.list.AbstractList;

/**
 * TODO
 * 动态数组
 * @author 衍方
 * @date 2020/8/31 - 15:40
 */
@SuppressWarnings("unchecked")
public class DynamicArray<E> extends AbstractList<E> {

    /**
     * 数组所有元素及内存地址指向
     */
    private E[] elements;

    //数组的默认初始化值
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 带参构造函数，参数是数组初始化值
     * @param capacity
     */
    public DynamicArray(int capacity) {

        //初始化数组长度，不允许传入的长度为负数
        if (capacity < 0){
            throw new IllegalArgumentException("初始化容量: "+
                    capacity + " | " + "error -> capacity不能小于0" );
        }
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        //通过new Object[]，动态数组可以实现多对象化
        elements = (E[]) new Object[capacity];
    }

    /**
     * 构造函数，将数组初始化
     */
    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 设置index位置的元素值
     * @param index
     * @param element
     * @return old
     */
    public E set(int index,E element){
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 获取数组index位置的元素
     * @param index
     * @return elements[index];
     */
    public E get(int index){
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    public int indexOf(E element){
        //如果元素为空
        if (element == null){
            for (int i = 0;i < size;i++){
                if (elements[i] == null) return i;
            }
        }else {
        //元素不为空
            for (int i = 0;i < size;i++){
                if (element.equals(elements[i])) return i;
            }
        }
        //查无此元素
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到数组指定位置
     * @param index
     * @param element
     */
    public void add(int index,E element){
        //检查索引是否合法
        rangeCheckForAdd(index);
        //检查数组容量是否足够
        ensureCapacity(size + 1);

        for (int i = size;i > index;i--){
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 删除指定元素
     * @param element
     * @return
     */
    public E remove(E element){
        //调用indexOf获取索引，通过索引删除指定元素
        return remove(indexOf(element));
    }

    /**
     * 删除指定index位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
        rangeCheck(index);

        E old = elements[index];
        for (int i = index + 1;i < size;i++){
            elements[i - 1] = elements[i];
        }
        //将数组原来尾部最后的元素所在的位置置为null，释放原来地址引用对应的对象内存
        elements[--size] = null;
        return old;
    }

    /**
     * 清除所有元素
     */
    public void clear() {

        // 数组清空，应该根据情况缩容，避免内存浪费
        if (elements != null && elements.length > DEFAULT_CAPACITY) {
            elements = (E[]) new Object[DEFAULT_CAPACITY];
        }else {
            for (int i = 0; i < size; i++) {
                elements[i] = null;
            }
        }
        size = 0;
    }

    /**
     * 保证要有capacity的容量
     * @param capacity
     */
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        //如果数组容量足够，reyurn
        if (oldCapacity >= capacity) return;

        //否则的话，数组扩容，数组扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        //将原有数组元素复制到新数组中
        for (int i = 0;i < size;i++){
            newElements[i] = elements[i];
        }
        //指向新数组
        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    /**
     * 如果内存使用比较紧张，动态数组有比较多的剩余空间，可以考虑进行缩容操作
     * 例如：经过扩容后的数组很大，在经过删除操作后，数组元素数量不多，而数组所占空间够大
     * 比如剩余空间占总容量的一半时，就进行缩容 (规则可以自定义)
     */
    private void trim(){
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity >> 1;

        //如果元素的数量大于缩容后数组长度，或者小于初始量，不进行缩容操作
        if (size >= (newCapacity) || size <= DEFAULT_CAPACITY) return;;

        // 剩余空间还很多
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

        System.out.println(oldCapacity + "缩容为" + newCapacity);
    }


    /**
     * 重写toString函数，打印数组
     * @return
     */
    @Override
    public String toString() {
        // size=3, [99, 88, 77]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);

        }
        string.append("]");
        return string.toString();
    }
}


