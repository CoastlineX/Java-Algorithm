package com.kalton.data_structure.stack;

/**
 * 数据结构 - 栈测试类
 *
 * @author 衍方
 * @date 2020/9/12 - 17:04
 * @link https://github.com/kaltons/Java-Algorithm
 */
public class Main {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
