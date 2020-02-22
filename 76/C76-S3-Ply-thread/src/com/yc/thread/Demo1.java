package com.yc.thread;

/**
 * join ����
 */
public class Demo1 {
	
	public static void main(String[] args) {
		/**
		 * ʹ�������ഴ���߳�
		 */
		Thread t1 = new Thread("����"){
			@Override
			public void run() {
				for(int i=0; i<100;i++){
					System.out.println(Thread.currentThread().getName() + i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t2 = new Thread("����"){
			@Override
			public void run() {
				for(int i=0; i<100;i++){
					
					if(i==50){
						try {
							// �����̵߳�ǰ���� ���� ״̬
							t1.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					System.out.println(Thread.currentThread().getName() + i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		t1.start();
		t2.start();
		
	}

}
