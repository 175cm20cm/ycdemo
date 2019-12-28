package com.yc.http.tomcat.v3;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

public class HttpServletResponse {
	
	private HashMap<String, String> headerMap = new HashMap<>();
	private int status;
	private String statusMsg;
	private HttpServletRequest request;
	private OutputStream out;
	
	public HttpServletResponse(HttpServletRequest request, OutputStream out) {
		this.request = request;
		this.out = out;
	}
	
	public void setHeader(String key, String value){
		headerMap.put(key, value);
	}
	
	public void setStatus(int status, String statusMsg){
		this.status=status;
		this.statusMsg = statusMsg;
	}
	
	// �ύ,���������ҳ��·��������ҳ�������
	public void commit() throws IOException{
		// ��Ӧͷ��
		out.write(("HTTP/1.1 "+status+" "+statusMsg+"\n").getBytes());
		// ��Ӧͷ��
		// Context-Type ���ص����ͱ��밴��httpЭ���׼����
		
		String contextType = getContextType(request.getRequestURI());
		
		out.write(("Context-Type: "+contextType+"\n").getBytes());
		
		// ����map���ϣ��������е� ��ֵ�� ����� ͷ����
		for( Entry<String,String> e : headerMap.entrySet() ){
			out.write((e.getKey()+": "+e.getValue()+"\n").getBytes());
		}
		
		// ���� CRLF
		out.write("\n".getBytes());
		// ��Ӧʵ��
		// out.write("<html><body>hello world��</body></html>".getBytes());
		
		File file;
		if(status == 404){
			file = new File(Server.WEBAPPS + "/photo/404.html");
		} else {
			// �ж� caw �����Ƿ�������
			String data = caw.toString();
			if(data.length() > 0){
				out.write(data.getBytes());
			} else {
				String filepath = Server.WEBAPPS + request.getRequestURI();
				file = new File(filepath);
				FileInputStream fos = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int count;
				while ((count = fos.read(buffer)) > 0) {
					out.write(buffer, 0, count);
				}
				fos.close();
			}
		}
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
	}

	// ��ʱ���� ���ݵ� ��
	private CharArrayWriter caw = new CharArrayWriter();
	private PrintWriter pw = new PrintWriter(caw);
	public PrintWriter getWriter() {
		return pw;
	}

	public int getStatus() {
		return status;
	}

}
