package com.yc.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * ��������������ģʽ
 * 
 * @author Administrator
 *
 */
public class Company {

	private List<Object> list = new ArrayList<>();

	public static void main(String[] args) {
		Company c = new Company();
		c.product();
		c.comsume();
	}

	/**
	 * ��������
	 */
	public void product() {

		new Thread("������") {
			public void run() {
				try {
					while (true) {
						synchronized (list) {
							for (int i = 0; i < 10; i++) {
								System.out.println(Thread.currentThread().getName() + "��������һ����Ʒ");
								Thread.sleep(200);
								list.add(new Object());
							}
							list.notify();
							// �ȴ������ߵ�֪ͨ
							list.wait();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	/**
	 * ���ѷ���
	 */
	public void comsume() {
		new Thread("������") {
			public void run() {
				try {
					synchronized (list) {
						while (true) {
							while (list.size() > 0) {
								System.out.println(Thread.currentThread().getName() + "��������һ����Ʒ");
								Thread.sleep(100);
								list.remove(0);
							}
							// ֪ͨ�����߿�ʼ����
							list.notify();
							// �ȴ������������
							list.wait();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
