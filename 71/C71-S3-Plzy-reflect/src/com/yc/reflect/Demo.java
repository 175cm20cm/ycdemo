package com.yc.reflect;

//������
import java.lang.reflect.*;
import java.util.HashMap;

public class Demo {
	private String name;
	private String sn;
	private int age;
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	
	private static void action1(Object obj) throws NoSuchMethodException, SecurityException {
		//ͨ�������ȡ���Ժͷ���
		Method m;
		m=obj.getClass().getMethod("setName", String.class);
	}
	public static void action(Object obj) throws NoSuchFieldException, SecurityException {
		//ͨ�������ȡ���Ժͷ���
		Field f;
		f=obj.getClass().getDeclaredField("name");  //�������ֻ�ȡ�������
		Field[] fs=obj.getClass().getDeclaredFields();   //��ȡ��Ķ������������
		//obj.getClass().getField(name);   //��ȡָ���Ĺ�������
		//obj.getClass().getFields();  //��ȡ���еĹ�������
	}
}


