package com.yc.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����ע��
 * @author LiuZiyuan
 *
 */

//ע���ע��Target  �����ע�ⶨ���λ��
@Target(value= {ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	public String name() default "test";
	
	public int type() default 0;
	
	public  String value();
	
	//���ע����һ��value ���� ��ôvalue ���Ǹ�ע���Ĭ�����ԣ�Ĭ�����Կ��Բ���д������
	//��ǰ�᣺ֻ����һ������ʱ��
}


