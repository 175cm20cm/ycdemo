package com.yc.junit;

import java.lang.reflect.InvocationTargetException;

public class MyJunitTest {
	@Before
	public void prepare() {
		System.out.println("before:���еĲ��Է���֮ǰ��Ҫִ���������");
	}
	@After
	public void After() {
		System.out.println("after:���з�������֮��Ҫִ������ķ���");
	}
	@Test("eat")
	public void eat() { 
		System.out.println("===ǡ��===");
	}
	@Test("run")
	public void run() { 
		System.out.println("===�ܲ�===");
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
		ParseJunit pj=new ParseJunit();
		pj.parseMethod(MyJunitTest.class);
	}
	
}
