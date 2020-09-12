package com.kalton.data_structure.linkedlist;

import com.kalton.data_structure.dynamic_array.DynamicArray;
import com.kalton.data_structure.list.List;
import com.kalton.data_structure.util.Asserts;

/**
 * TODO
 * 链表测试类
 * @author 衍方
 * @date 2020/9/5 - 13:09
 */
public class Main {

    static void testList(List<Integer> list) {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55); // [55, 11, 22, 33, 44]
        list.add(2, 66); // [55, 11, 66, 22, 33, 44]
        list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]

        list.remove(0); // [11, 66, 22, 33, 44, 77]
        list.remove(2); // [11, 66, 33, 44, 77]
        list.remove(list.size() - 1); // [11, 66, 33, 44]

        Asserts.test(list.indexOf(44) == 3);
        Asserts.test(list.indexOf(22) == List.ELEMENT_NOT_FOUND);
        Asserts.test(list.contains(33));
        Asserts.test(list.get(0) == 11);
        Asserts.test(list.get(1) == 66);
        Asserts.test(list.get(list.size() - 1) == 44);

        System.out.println(list);
    }

    public static void main(String[] args) {
        testList(new DynamicArray<>());
        testList(new LinkedList<>());
        testList(new Virtual_LinkedList<>());
    }
}
