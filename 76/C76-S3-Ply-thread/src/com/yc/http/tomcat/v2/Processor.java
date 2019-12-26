package com.yc.http.tomcat.v2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * ������
 * 
 * @author Administrator
 *
 */
public class Processor {

	private Socket socket;
	private InputStream in;
	private OutputStream out;

	/**
	 * ���췽��
	 * 
	 * @param socket
	 * @throws IOException
	 */
	public Processor(Socket socket) throws IOException {
		// ��socket ������������
		this.socket = socket;
		// ��ȡ ��������� ����������
		in = socket.getInputStream();
		out = socket.getOutputStream();
	}

	/**
	 * ����ķ���
	 * 
	 * @throws IOException
	 */
	public void process() throws IOException {

		// ��������
		HttpServletRequest request = new HttpServletRequest(in);
		
		// ������Ӧ����
		HttpServletResponse response = new HttpServletResponse(request,out);
		
		File file = new File(Server.WEBAPPS + request.getRequestURI());
		
		if(file.exists()){
			response.setStatus(200, "OK");
		} else {
			response.setStatus(404, "Not Found");
		}
		
		response.commit();
		
		socket.close();

	}

}
