package com.yc.thread;

/**
 * �̷߳���
 * 1�����߳�   Ĭ������£����̻߳�ȵ����е����߳�ִ����Ž������������̵߳Ĵ����Ѿ�ִ���꣬���߳���Ȼ��ȴ�
 * 2�����߳�
 * 2�������̣߳��ػ��̣߳� setDaemon(true)����������ǰ���ú�
 *
 */
public class Demo2 {
	
	
	public static void main(String[] args) {
		
		
		
		Thread t1 = new Thread("�����߳�"){
			@Override
			public void run() {
				for(int i=0; i<100;i++){
					System.out.println(Thread.currentThread().getName() + i);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		// ���̲߳���ȵ������߳�ִ���꣬�Ž���
		t1.setDaemon(true);
		
		t1.start();
		
		for(int i=0; i<100;i++){
			System.out.println(Thread.currentThread().getName() + i);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("������Ĵ����Ѿ�ִ�����");
	}

}
