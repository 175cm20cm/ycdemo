package com.yc.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Demo1 {
	
	/**
	 * ������
	 * ISO 7����·Э��
	 * 
	 * TCP/IP
	 * 
	 * IP �Ǽ��������·�е����ֵ�ַ 192.168.1.120;   IPv4 ==> IPv6
	 * �˿ڣ�0~65535    1024���µĶ˿���Ԥ����ϵͳ��
	 */
	
	/**
	 * URL �ࣺ���ڷ��������ϵ���Դ
	 * 
	 * Socket �ࣺ�������������֮���ͨѶ
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		// URL ȫ����Դ��λ��
		URL url = new URL("http://www.hyycinfo.com/index.html");
		URLConnection conn = url.openConnection();
		System.out.println(conn);
		conn.getContentType();// ������Դ����
		conn.getLastModified();//����޸�ʱ��
		conn.getContentLength(); //��ȡ��С
		
		InputStream in = conn.getInputStream();
		
		byte[] buffer = new byte[1024];
		int count;
		while(   (  count = in.read(buffer)  )  > 0   ){
			String s = new String(buffer,0,count,"utf-8");
			System.out.println(s);
		}
		
	}

}
