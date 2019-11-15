package com.yc.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class DemoTest {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		Demo d= new Demo();
		d.setName("����");
		//action(d);
		
		Demo e=new SubDemo();
		e.setName("����");
		//action1(d);
		
		d.setAge(18);
		d.setSn("123456");
		
		//������ת��map
		toMap(d,new HashMap<String,Object>());
		
	}
	public static void toMap(Object obj, HashMap<String, Object> map) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		Field[] fs=obj.getClass().getDeclaredFields();
		
		for(Field f:fs) {
			//��ȡ˽�����Է���һ��
			//f.setAccessible(true);
			//��ȡ��ǰ����ֵ
			//Object value=f.get(obj);
			
			//��ȡ˽�����Է�������
			//ͨ�����Է�������ȡ�����ã�����ֵ
			//f.getType(); //getType() ���ڷ������Ե�����    name=>> Stirng   age==>>Integer
			String getMethodName = "get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
			Method getMethod=obj.getClass().getMethod(getMethodName);
			// invoke ʵ�з����ķ���
			Object value = getMethod.invoke(obj); //obj.getXxxxx();
			//��ȡ������
			String name=f.getName();
			map.put(name, value);
			
//			//��ȡ��ǰ���Ե�ֵ
//			Object value = f.get(obj);
//			//��ȡ������
//			String name = f.getName();
//			map.put(name,value);
			
			
		}
		System.out.println(map);
	}
}
