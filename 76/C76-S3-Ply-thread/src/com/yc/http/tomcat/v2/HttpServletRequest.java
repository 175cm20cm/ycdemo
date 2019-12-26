package com.yc.http.tomcat.v2;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class HttpServletRequest {
	private String method;
	private String requestURI;
	private String protocol;
	private HashMap<String, String> headerMap = new HashMap<>();

	public HttpServletRequest(InputStream in) throws IOException {
		// �� ����ͷ�е� ���� ����Ϊ �����ַ�������
		byte[] buffer = new byte[1024];
		int count = in.read(buffer);
		if (count > 0) {
			String reqstring = new String(buffer, 0, count);
			System.out.println(reqstring);

			String[] lines = reqstring.split("\r\n");
			method = lines[0].split("\\s")[0];
			// ��ȡ��Դ·��
			requestURI = lines[0].split("\\s")[1];
			protocol = lines[0].split("\\s")[2];

			// �� ͷ���еļ�ֵ�� ������ map �� \r\n CRLF
			
			for (int i = 1; i < lines.length && lines[i - 1].isEmpty() != true; i++) {
				// ֻ������ͷ�������
				String[] kv = lines[i].split(":\\s");
				headerMap.put(kv[0], kv[1]);
			}
		}
	}

	public String getMethod() {
		return method;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public String getHeader(String headerName) {
		return headerMap.get(headerName);
	}
	
	/**
	 * ��ȡ��ֵ�������
	 * 	URL���ӣ�127.0.0.1/photo/index.html?user=root&pwd=123
	 * 	���Էֱ�� user �� pwd ��ֵ
	 * @param parameter
	 * @return
	 */
	public String getParameter(String parameter){
		return null;
	}
	
	/**
	 * ��ȡ�������������
	 * 	URL���ӣ�127.0.0.1/photo/index.html?like=book&like=movie&user=root&pwd=123
	 * 	��ȡ like ����������2��Ԫ��{"book","movie"}������
	 * @param parameter
	 * @return
	 */
	public String[] getParameterValues(String parameter){
		return null;
	}
	
	/**
	 * �������2������֮����������������
	 * 1������ POST �����е��������
	 * 		�Ա���ʽ����POST���󣬷�������������۲챨�Ľṹ���������������
	 * 		ע�⣺�������ʵ�����ݻ�ȡ������������� byte[] buffer �������С
	 * 
	 * 2���۲� �ļ��ϴ���ʽ�ύ�������ģ�����Ҫ�����������ӡ�����Ľṹ
	 */
}
