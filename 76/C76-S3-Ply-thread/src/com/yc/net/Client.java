package com.yc.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("127.0.0.1", 8888);

		// ��ȡ�ͻ��˵�ַ����
		InetAddress addr = socket.getInetAddress();
		System.out.println(addr.getHostName());
		System.out.println(Arrays.toString(addr.getAddress()));

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		Scanner sc = new Scanner(System.in);

		// �����߳�
		new Thread() {
			public void run() {
				while (true) {
					try {
						byte[] buffer = new byte[1024];
						// read ��Ӵһ����������
						int count;
						count = in.read(buffer);
						String s = new String(buffer, 0, count);
						System.out.println(s);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}.start();

		// д���߳�
		new Thread() {
			public void run() {
				while (true) {
					try {
						out.write(sc.nextLine().getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}.start();

	}

}