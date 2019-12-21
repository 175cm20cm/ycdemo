package com.yc.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Socket �׽��֣���̨�����������������ĶԽ�
 */
public class Server {

	public static final String FILE_HOME = "f:/";

	public static void main(String[] args) throws IOException {

		// ���� Socket �����
		ServerSocket server = new ServerSocket(8888);
		System.out.println("�����������ɹ����ȴ��ͻ�������: 8888");
		// server �ȴ��ͻ��˵����ӣ���������״̬
		Socket socket = server.accept();

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
						if (s.startsWith("�ļ���")) {
							String filename = s.substring("�ļ���".length());
							filename = filename.substring(filename.lastIndexOf("/") + 1);
							saveFile(in, filename);
						} else {
							System.out.println(s);
						}
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
						String msg = sc.nextLine();
						out.write(msg.getBytes());
						// �ļ���e:/a.html
						if (msg.startsWith("�ļ���")) {
							String filename = msg.substring("�ļ���".length());
							sendFile(out, filename);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}.start();

	}

	static void saveFile(InputStream in, String filename) throws IOException {
		// ��ȡ���͹������ļ���С
		DataInputStream dis = new DataInputStream(in);
		long filesize = dis.readLong();
		// �����ļ������
		FileOutputStream fos = new FileOutputStream(FILE_HOME + filename);
		try {
			byte[] buf = new byte[1024];
			int count;
			int readsize = 0;
			while (readsize < filesize && (count = in.read(buf)) > 0) {
				fos.write(buf, 0, count);
				readsize += count;
			}
		} finally {
			fos.close();
		}
		System.out.println("�ļ�����ɹ���" + FILE_HOME + filename);
	}

	static void sendFile(OutputStream out, String filepath) throws IOException {
		// �����ļ���С�����öԷ�����֪���ļ���ʱ�����ļ��Ľ���
		DataOutputStream dos = new DataOutputStream(out);
		File f = new File(filepath);
		dos.writeLong(f.length());

		// ���ļ����͸��Է�
		FileInputStream fis = new FileInputStream(f);
		try {
			byte[] buf = new byte[1024];
			int count;
			while ((count = fis.read(buf)) > 0) {
				out.write(buf, 0, count);
			}
		} finally {
			fis.close();
		}
		System.out.println("�ļ����ͳɹ���" + filepath);
	}
}
