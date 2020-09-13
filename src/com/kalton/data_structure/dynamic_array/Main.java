package com.kalton.data_structure.dynamic_array;

/**
 * TODO
 * 动态数组测试类
 * @author 衍方
 * @date 2020/8/31 - 15:39
 */
public class Main {

    public static void main(String[] args) {
        DynamicArray<Integer> ints  = new DynamicArray<>();
        ints.add(10);
        ints.add(10);
        ints.add(22);
        ints.add(33);
        System.out.println(ints);
    }
}
