package com.kalton.data_structure.dynamic_array;

/**
 * 数据结构 - 动态数组测试类
 *
 * @author 衍方
 * @date 2020/8/31 - 15:39
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class Main {

    public static void main(String[] args) {
        // 常规测试
        DynamicArray<Integer> ints  = new DynamicArray<>();
        ints.add(10);
        ints.add(10);
        ints.add(22);
        ints.add(33);
        System.out.println(ints);

//        // 修复初始化数组长度为负数的情况 —— 测试
//        try {
//            DynamicArray<Integer> arr = new DynamicArray<>(-1);
//            arr.add(10);
//            arr.add(20);
//            System.out.println(arr);
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
    }
}
