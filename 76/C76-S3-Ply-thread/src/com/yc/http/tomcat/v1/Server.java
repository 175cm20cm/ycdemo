package com.yc.http.tomcat.v1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	// ���Եĵ�ַ : 127.0.0.1/photo/index.html
	public final static String WEBAPPS = "/Tomcat/webapps";

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(80);
		System.out.println("�����������ɹ���80");
		
		Socket socket;
		while ((socket = server.accept()) != null) {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			byte[] buffer = new byte[1024];
			int count = in.read(buffer);
			if(count > 0 ){
				// ��ȡ������
				String reqstring = new String(buffer, 0, count);
				System.out.println(reqstring);
				
				String[] lines = reqstring.split("\r\n");
				// ��ȡ��Դ·��
				String srcpath = lines[0].split("\\s")[1];
	
				// ��Ӧͷ��
				out.write("HTTP/1.1 200 OK\n".getBytes());
				// ��Ӧͷ��
				// Context-Type ���ص����ͱ��밴��httpЭ���׼����
				
				String contextType = getContextType(srcpath);
				
				out.write(("Context-Type: "+contextType+"\n").getBytes());
				// ���� CRLF
				out.write("\n".getBytes());
				// ��Ӧʵ��
				// out.write("<html><body>hello world��</body></html>".getBytes());
				
				String filepath = WEBAPPS + srcpath;
				File file = new File(filepath);
				if(file.exists() == false){
					file = new File(WEBAPPS + "/photo/404.html");
				}
				FileInputStream fos = new FileInputStream(file);
				while ((count = fos.read(buffer)) > 0) {
					out.write(buffer, 0, count);
				}
				fos.close();
				
			}
			// �뷵��һ�� html �ļ�
			// ������Ӧ����ر� socket
			socket.close();
		}

		/**
		 * �����ģ�
		 * ����ͷ�У�  GET /index.jsp?a=100&b=200 HTTP/1.1
		 * 		ͷ�зֳ�3��  ������ �ո�  ��������Դ��ַ �ո� Э��汾
		 * ͷ��ÿ��һ����ֵ�ԣ���ð�ŷָ�
		 * 		����������Ϣ���������
		 * CRLF�����У����÷ָ�ͷ���ʵ��
		 * ʵ�壺POST ��ʵ������  GET û��ʵ��
		 */
	}

	private static String getContextType(String srcpath) {
		int beginIndex = srcpath.lastIndexOf(".");
		String suffix = srcpath.substring(beginIndex);
		switch (suffix) {
		case ".css":
			return "text/css";
		case ".js":
			return "application/javascript";
		case ".jpg":
			return "image/jpeg";
		case ".gif":
			return "image/gif";
		case ".png":
			return "image/png";
		}
		return "text/html";
		
		/** ��ҵ��
		 *  ��ʹ�� tomcat/conf/web.xml �� ���ã�ʵ���ļ���׺��contexttype��ӳ��
		 */
	}

}
