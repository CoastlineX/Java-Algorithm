package com.kalton.algorithm.sorting;

import java.util.Arrays;

/**
 * TODO
 * 冒泡排序
 * @author 衍方
 * @desc
 * @date 2020/9/5 - 0:01
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = new int[]{5,8,6,3,9,2,1,7};
        cockTailSort(array);
        System.out.println(Arrays.toString(array));
    }

//    /**
//     * 第一版
//     * @param arr
//     */
//    public static void bubbleSort(int[] arr){
//        if (arr == null || arr.length == 0) return;
//        for (int i = 0; i < arr.length - 1; i++) {
//
//            for (int j = 0; j < arr.length - 1 -i; j++) {
//
//                int tmp = 0;
//                //升序排序>,降序排序<
//                if (arr[j] > arr[j + 1]){
//                    tmp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = tmp;
//                }
//
//            }
//        }
//    }

//    /**
//     * 第二版
//     * @param arr
//     */
//    public static void bubbleSort(int[] arr){
//        if (arr == null || arr.length == 0) return;
//        for (int i = 0; i < arr.length - 1; i++) {
//            //是否已经有序的标记，默认有序
//            boolean isSorted = true;
//            for (int j = 0; j < arr.length - 1 -i; j++) {
//                int tmp = 0;
//                //升序排序>,降序排序<
//                if (arr[j] > arr[j + 1]){
//                    tmp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = tmp;
//                    //发生元素交换，序列仍是无序状态
//                    isSorted = false;
//                }
//            }
//            if (isSorted){
//                break;
//            }
//        }
//    }

//    /**
//     * 第3版
//     * @param arr
//     */
//    public static void bubbleSort(int[] arr){
//        if (arr == null || arr.length == 0) return;
//        //记录记录下来最后一次元素交换的位置
//        int lastExchangeIndex = 0;
//        //无序数列的边界，每次比较只需要比到这里为止
//        int sortBorder = arr.length-1;
//        for (int i = 0; i < arr.length - 1; i++) {
//            //是否已经有序的标记，默认有序
//            boolean isSorted = true;
//            for (int j = 0; j < sortBorder; j++) {
//                int tmp = 0;
//                //升序排序>,降序排序<
//                if (arr[j] > arr[j + 1]){
//                    tmp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = tmp;
//                    //发生元素交换，序列仍是无序状态
//                    isSorted = false;
//                    //更新为最后一次交换元素的位置
//                    lastExchangeIndex = j;
//                }
//            }
//            //更新无序数列的边界
//            sortBorder = lastExchangeIndex;
//            if (isSorted){
//                break;
//            }
//        }
//    }

    /**
     * 鸡尾酒排序
     * @param arr
     */
    public static void cockTailSort(int[] arr){
        if (arr == null || arr.length == 0) return;
        // 记录右侧最后一次交换的位置
        int lastRightExchangeIndex = 0;
        // 记录左侧最后一次交换的位置
        int lastLeftExchangeIndex = 0;
        // 无序数列的右边界，每次比较只需要比到这里为止
        int rightSortBorder = arr.length - 1;
        // 无序数列的左边界，每次比较只需要比到这里为止
        int leftSortBorder = 0;

        //i设置为1，代表从第1轮开始
        for (int i = 1; i < arr.length; i++) {
            boolean isSorted = true;
            //奇数，从左到右
            if (i % 2 != 0) {
                for (int j = leftSortBorder; j < rightSortBorder; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        //发生元素交换，序列仍是无序状态
                        isSorted = false;
                        //更新为右侧最后一次交换元素的位置
                        lastRightExchangeIndex = j;
                    }
                }
            } else {
                for (int j = rightSortBorder; j > leftSortBorder; j--) {
                    if (arr[j] < arr[j - 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = temp;
                        //发生元素交换，序列仍是无序状态
                        isSorted = false;
                        //更新为左侧最后一次交换元素的位置
                        lastLeftExchangeIndex = j;
                    }
                }
            }
            //更新无序数列的左边界
            leftSortBorder = lastLeftExchangeIndex;
            //更新无序数列的右边界
            rightSortBorder = lastRightExchangeIndex;
            if (isSorted) {
                break;
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
        int min = left;
        //记录每轮找到的最大值的下标
        int max = right;
        //当right >= left时，列表已经有序
        while(left < right) {
            min = left;     //每轮开始前，默认无序列表起点为最小值
            max = right;    //每轮开始前，默认无序列表终点为最大值
            //判断每轮循环是否有找到最小/大值，默认false，找到置为true
            boolean isFindMin = false,isFindMax = false;
            //指针i从左往右扫描，找出最小值，最大值
            for (int i=left; i<=right; i++) {
                if (arr[i]<arr[min]) {
                    min = i;    //通过比较，记录最小值的下标
                    isFindMin = true;
                }
                if(arr[i]>arr[max]) {
                    max = i;    //通过比较，记录最大值的下标
                    isFindMax = true;
                }
            }
            //如果找到最小值，交换
            if (isFindMin){
                temp = arr[left];
                arr[left] = arr[min];
                arr[min] = temp;
            }
            //如果找到最小值，交换
            if (isFindMax){
                temp = arr[right];
                arr[right] = arr[max];
                arr[max] = temp;
            }

            //确定最小/大值，指针向中间移动
            left++;right--;
        }
    }

}
