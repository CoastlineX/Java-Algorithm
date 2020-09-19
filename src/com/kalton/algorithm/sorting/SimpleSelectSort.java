package com.kalton.algorithm.sorting;

import java.util.Arrays;

/**
 * TODO
 * 简单选择排序
 * @author 衍方
 * @desc
 * @date 2020/9/5 - 13:21
 */
public class SimpleSelectSort {

    public static void main(String[] args) {
        int[] array = new int[]{5,1,6,3,9,2,7,0};
//        straightSelectSort(array);
        betterSelectSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void straightSelectSort(int[] arr){

        //i不需要 = 数组最尾部元素，因为后面没有值对比了
        for (int i = 0; i < arr.length - 1; i++) {
            //设置每次循环的起始点为最小/大值
            int min = arr[i];
            //记录下最小/大值的下标
            int index = i;
            //哨兵，记录是否找到最值，默认false
            boolean isSwap = false;
            for (int j = i + 1; j < arr.length; j++) {
                //升序排序>,降序排序<
                if (min > arr[j]){
                    min = arr[j];
                    index = j;
                    //找到最值，设置为true
                    isSwap = true;
                }
            }
            if (isSwap){
                //一轮对比完成后，将默认的最小值赋予到找到的最值下标位置
                arr[index] = arr[i];
                //把找到的真实最值放到前面
                arr[i] = min;
            }
        }
    }

    //简单选择排序的优化方法
    public static void betterSelectSort(int[] arr) {

        //left指针指向无序边界起点，right指针指向终点，temp用作临时变量交换值
        int left,right,temp;
        //默认指向无序列表起点
        left = 0;
        //默认指向无序列表终点
        right = arr.length - 1;
        //记录每轮找到的最小值的下标
        int min;
        //记录每轮找到的最大值的下标
        int max;
        //当right >= left时，列表已经有序
        //记录循环的次数
        int index = 0;
        while(left < right) {
            min = left;     //每轮开始前，默认无序列表起点为最小值
            max = right;    //每轮开始前，默认无序列表终点为最大值
            //指针i从左往右扫描，找出最小值，最大值
            for (int i=left; i<=right; i++) {
                if (arr[i] <= arr[min]) {
                    min = i;    //通过比较，记录最小值的下标
                }
                if(arr[i] >= arr[max]) {
                    max = i;    //通过比较，记录最大值的下标
                }
            }
            index++;
            if (min == left && max == right){
                System.out.println("第" + index + "轮循环没有找到最值，无需交换");
            }else if (min == right && max == left){
                //交换一次即可，交换两次的话，序列翻转，相当于没有交换
                temp = arr[left];
                arr[left] = arr[min];
                arr[min] = temp;
            }else {
                //找到最小值，交换
                temp = arr[left];
                arr[left] = arr[min];
                arr[min] = temp;

                //找到最大值，交换
                temp = arr[right];
                arr[right] = arr[max];
                arr[max] = temp;
            }
            //确定最小/大值，指针向中间移动
            left++;right--;
        }
    }

}
