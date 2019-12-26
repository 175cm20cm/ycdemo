package com.yc.http.tomcat.v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static final String WEBAPPS = "/Tomcat/webapps";

	/**
	 * ����������
	 * @throws IOException 
	 */
	public void startup() throws IOException{
		// ����Socket������
		ServerSocket server = new ServerSocket(80);
		System.out.println("�����������ɹ���80");
		
		// ѭ�����������������
		Socket socket;
		while ((socket = server.accept()) != null) {
			final Socket innerSocket = socket;
			// �������̣߳���������������ͻ�������
			new Thread(){
				public void run(){
					// ��������������
					Processor p;
					try {
						p = new Processor(innerSocket);
						p.process();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		server.close();
	}
	
	/**
	 * ������ֹͣ
	 */
	public void shutdown(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		new Server().startup();
		
	}
	
}
