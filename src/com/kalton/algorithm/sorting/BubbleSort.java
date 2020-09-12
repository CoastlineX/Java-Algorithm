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
}
