package com.kalton.data_structure.queue;

import com.kalton.data_structure.queue.circle.CircleDeque;
import com.kalton.data_structure.queue.circle.CircleQueue;

/**
 * TODO
 * 数据结构 - 队列测试类
 * @author 衍方
 * @desc
 * @date 2020/9/13 - 13:01
 */
public class Main {

    //双向链表实现队列 —— 测试
    static void test1() {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

    //双栈实现队列 —— 测试
    static void test2() {
        QueueByStack<Integer> queue = new QueueByStack<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);
        System.out.println(queue.size());
        while (!queue.isEmpty()) {
            System.out.println("队头元素 -> " + queue.front());
            System.out.println("出队元素 -> " + queue.deQueue());
        }
        //清空
        queue.clear();
        System.out.println(queue.isEmpty());
    }

    //双端链表 —— 测试
    static void test3(){
        Deque<Integer> queue = new Deque<>();
		queue.enQueueFront(11);
		queue.enQueueFront(22);
		queue.enQueueRear(33);
		queue.enQueueRear(44);

		/* 尾  44  33   11  22 头 */
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueRear());
		}
    }

    //循环队列 —— 测试
    static void test4(){
        CircleQueue<Integer> queue = new CircleQueue<>();
        // 0 1 2 3 4 5 6 7 8 9
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        // null null null null null 5 6 7 8 9
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }

        // 15 16 17 18 19 5 6 7 8 9
        for (int i = 15; i < 20; i++) {
            queue.enQueue(i);
        }

        //queue.clear();

        // 验证扩容机制
        //for (int i = 15; i < 25; i++) {
        //    queue.enQueue(i);
        //}

        System.out.println(queue);
        while (!queue.isEmpty()) {
            if (queue.size() != 1){
                System.out.print(queue.deQueue() + "-->");
            }else {
                System.out.print(queue.deQueue());
            }
        }
    }

    //循环双端队列 —— 测试
    static void test5() {
        CircleDeque<Integer> queue = new CircleDeque<>();
        // 头5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 尾

        // 头 8 7 6  5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 null null 10 9 尾
        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i + 1);
            queue.enQueueRear(i + 100);
        }

        // 头 null 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null null 尾
        for (int i = 0; i < 3; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }

        // 头 11 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null 12 尾
        queue.enQueueFront(11);
        queue.enQueueFront(12);

        //queue.clear();
        System.out.println(queue);
        while (!queue.isEmpty()) {
            if (queue.size() != 1){
                System.out.print(queue.deQueueFront() + "-->");
            }else {
                System.out.print(queue.deQueueFront());
            }
        }
    }


    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
    }
}
