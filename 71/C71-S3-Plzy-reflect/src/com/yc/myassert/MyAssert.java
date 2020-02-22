package com.yc.myassert;

public class MyAssert {
	public static void assertEquals(Object expected, Object actual) {
		if(!expected.equals(actual)) {
			failNotEquals(expected,actual);
		}
	}
	
	public static void assertNotNullEquals(Object expected) {
		if(expected.equals(null)) {
			fail("�ö���Ϊ��");
		}
	}
	
	public static void assertTrue(boolean expected) {
		if(expected==false) {
			fail("ʵ����Ϊ"+expected);
		}
	}

	public static void failNotEquals( Object expected, Object actual) {
		String msg="�ڴ�ֵΪ��"+expected+"��ʵ��ֵ��"+actual;
		fail(msg);
	}

	public static void fail(String msg) {
		if (msg == null) {
			throw new AssertionError();
		}
		throw new AssertionError(msg);
	}

}
