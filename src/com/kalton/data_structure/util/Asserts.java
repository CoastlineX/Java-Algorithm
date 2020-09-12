package com.kalton.data_structure.util;

/**
 * 链表类测试工具
 */
public class Asserts {
	public static void test(boolean value) {
		try {
			if (!value) throw new Exception("测试未通过");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
