package com.yc.thread;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		
		/**
		 * �趨���ȼ����������߳�����ǰ����
		 */
		
		Thread t1 = new MyThread1();
		
		t1.setPriority(10);  // 1 ��ʾ������ȼ�
		t1.start();
		
		Runnable r1 = new MyRunnable2();
		Thread t2 = new Thread(r1);
		
		t2.setPriority(1);  // 10 ��ʾ������ȼ�
		t2.start();
		
		/**
		 * �̴߳�����ʽ��
		 * 1���̳� Thread ��
		 * 2��ʵ�� Runnable �ӿ�
		 */

	}

	public void demo() throws InterruptedException {
		// ���߳� main

		Thread t = Thread.currentThread();// ���ص�ǰ�̶߳���

		t.getId(); // Ψһ��ʶ
		t.getName(); // ����
		t.getPriority(); // ���ȼ�
		t.getState(); // ״̬
		t.getThreadGroup(); // �߳���
		t.isAlive(); // �ж��߳��Ƿ��ڻ״̬
		t.isDaemon(); // �жϵ�ǰ�߳��Ƿ��Ǿ����߳�
		t.isInterrupted(); // �ж��߳��Ƿ����ж�״̬

		Thread.currentThread();// ���ص�ǰ�̶߳���
		Thread.sleep(1000); // �߳�����
		Thread.yield(); // �öɡ�����

		t.join(); // ����
		t.run(); // ���д����߼�����
		t.start(); // �����߳�
	}

}

class A{
	
}

class B extends A implements Runnable{

	@Override
	public void run() {
		
	}
	
}


class MyThread1 extends Thread{

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("���� " + i);
			/*try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}
	
}

class MyRunnable2 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("����Ϸ " + i);
			/*try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}
	
}


