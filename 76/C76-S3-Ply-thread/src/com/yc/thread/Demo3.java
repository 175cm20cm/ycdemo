package com.yc.thread;

import java.util.ArrayList;

/**
 * 
 * ˫ɫ��(1���м�¼)��ʹ��DBHelper д�뵽���ݿ�
 *
 */


public class Demo3 {

	ArrayList<Object> list = new ArrayList<>();

	public Demo3() {
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
	}

	/**
	 * synchronized ͬ���ؼ��� 
	 * 1�����ڷ���֮�� 
	 * 		ʵ���������� synchronized ������ ��ǰ����this��
	 * 		��̬�������� synchronized ������ ��ǰ�ࣨclass��
	 * 2�����ڴ����֮��
	 */
	public Object getObj() throws InterruptedException {

		// synchronized �еĶ�������Ҫ�����Ķ���
		synchronized (this) {
			// ��Դ����
			if (list.size() > 0) {
				Thread.sleep(10);
				return list.remove(0);
			}
		}

		return null;
	}

	public static void main(String[] args) {

		Demo3 demo3 = new Demo3();

		MyThread t1 = new MyThread(demo3);
		MyThread t2 = new MyThread(demo3);
		MyThread t3 = new MyThread(demo3);
		MyThread t4 = new MyThread(demo3);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}

class MyThread extends Thread {

	private Demo3 demo3;

	public MyThread(Demo3 demo3) {
		this.demo3 = demo3;
	}

	public void run() {

		Object obj;

		try {
			while ((obj = demo3.getObj()) != null) {
				System.out.println(Thread.currentThread().getName() + "��ȡ��Ԫ�أ�" + obj);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
