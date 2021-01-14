package com.kalton.algorithm.serach;

/**
 * 搜索算法 —— 二分查找
 *
 * @author 衍方
 * @date 2020/9/14 - 22:24
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class BinarySearch {

    public static void main(String[] args) {

        // 常规测试数据
        int[] arr = {8,11,19,23,27,33,45,55,67,98};

        // 变种1,2 测试数据
        int[] arr1 = {1,3,4,5,6,8,8,8,11,18};

        int[] arr2 = {3,4,6,7,10};

        // 常规
        System.out.println("迭代法 ——> " + bSerachByIteration(arr,19));
        System.out.println("递归法 ——> " + bSerachByRecursive(arr,19));

        // 变种一
        System.out.println("变种一 ——> " + binarySerach1(arr1,8));
        // 变种二
        System.out.println("变种二 ——> " + binarySerach2(arr1,8));
        // 变种三
        System.out.println("变种三 ——> " + binarySerach3(arr2,5));
        // 变种四
        System.out.println("变种四 ——> " + binarySerach4(arr2,5));
    }

    /**
     * 迭代法实现
     * @param arr
     * @param value
     * @return
     */
    public static int bSerachByIteration(int[] arr, int  value) {
        // 头部指针
        int low = 0;
        // 尾部指针
        int high = arr.length - 1;

        while (low <= high){
            int mid = low + ((high - low) >> 1);

            if (arr[mid] == value){
                return mid;
            }else if (arr[mid] > value){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 递归法
     * @param arr
     * @param value
     * @return
     */
    public static int bSerachByRecursive(int[] arr, int  value){
        return bsearch(arr,0,arr.length -1,value);
    }


    private static int bsearch(int[] arr, int low, int high, int value) {

        if (low > high) return -1;

        int mid =  low + ((high - low) >> 1);
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] > value) {
            return bsearch(arr, low, mid-1, value);

        } else {
            return bsearch(arr, mid+1, high, value);
        }
    }

    /**
     * 变种一: 查找第一个值等于给定值的元素
     * @param arr
     * @param value
     * @return
     */
    public static int binarySerach1(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (arr[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 变种二: 查找最后一个值等于给定值的元素
     * @param arr
     * @param value
     * @return
     */
    public static int binarySerach2(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == arr.length - 1) || (arr[mid + 1] != value)) return mid;
                else high = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变种三: 查找第一个大于等于给定值的元素
     * @param arr
     * @param value
     * @return
     */
    public static int binarySerach3(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (arr[mid] >= value) {
                if ((mid == 0) || (arr[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变种四: 查找第一个大于等于给定值的元素
     * @param arr
     * @param value
     * @return
     */
    public static int binarySerach4(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == arr.length - 1) || (arr[mid + 1] > value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
}
