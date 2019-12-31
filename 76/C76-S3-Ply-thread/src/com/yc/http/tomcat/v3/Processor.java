package com.yc.http.tomcat.v3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * ������
 * 
 * @author Administrator
 *
 */
public class Processor {
	
	private static Map<String,HttpServlet> servletMap = new HashMap<>();
	
	static {
		// ��Servlet ���õ���������
		servletMap.put("/photo/hello.do", new HelloServlet());
	}

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
		
		/**
		 * 1���жϵ�ǰ�������ַ�Ƿ��Ƕ�̬����
		 * 		�жϵ�ǰ��ַ�Ƿ��ڷ�������ע�ᣬ��ô���ݵ�ַ���Ի�ȡ�� Servlet ����
		 * 2������ǣ���ʹ�� Servlet �ķ����������󣬷��ؽ��
		 * 3�����������򵱳ɾ�̬������
		 */
		
		HttpServlet servlet = servletMap.get(request.getRequestURI());
		if(servlet != null){
			// ��̬����
			servlet.service(request, response);
			// Ĭ��������Ӧ�����
			if(response.getStatus() == 0) {
				response.setStatus(200, "OK");
			}
		} else {
			// ��̬����
			if(file.exists()){
				response.setStatus(200, "OK");
			} else {
				response.setStatus(404, "Not Found");
			}
		}
		
		response.commit();
		
		socket.close();

	}

}
