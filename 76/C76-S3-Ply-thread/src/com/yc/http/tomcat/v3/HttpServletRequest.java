package com.yc.http.tomcat.v3;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class HttpServletRequest {
	private String method;
	private String requestURI;
	private String protocol;
	private HashMap<String, String> headerMap = new HashMap<>();
	// ��������
	private HashMap<String, String[]> paramMap =  new HashMap<>();

	public HttpServletRequest(InputStream in) throws IOException {
		// �� ����ͷ�е� ���� ����Ϊ �����ַ�������
		byte[] buffer = new byte[1024 * 10];
		int count = in.read(buffer);
		if (count > 0) {
			String reqstring = new String(buffer, 0, count);
			System.out.println(count);
			System.out.println(reqstring);

			String[] lines = reqstring.split("\r\n");
			method = lines[0].split("\\s")[0];
			// ��ȡ��Դ·��
			requestURI = lines[0].split("\\s")[1];
			/**
			 * �� URL ��ַ�н��� �����������   ?a=100&a=200&a=300
			 */
			if(requestURI.contains("?")){
				int index = requestURI.indexOf("?");  // > 0
				String paramStr = requestURI.substring(index+1);
				String[] params = paramStr.split("&");
				for(String param: params){
					String[] nav = param.split("=");
					addParameter(nav[0], nav.length==1 ? "" : nav[1] );
				}
				// �������õ�ַ
				requestURI = requestURI.substring(0, index);
				System.out.println("���������"+paramMap);
			}
			
			protocol = lines[0].split("\\s")[2];

			// �� ͷ���еļ�ֵ�� ������ map �� \r\n CRLF
			
			for (int i = 1; i < lines.length; i++) {
				if(lines[i].isEmpty()){
					// CRLF
					break;
				}
				// ֻ������ͷ�������
				String[] kv = lines[i].split(":\\s");
				headerMap.put(kv[0], kv[1]);
			}
			
			/**
			 * �ڴ˴�����  POST �ύ�Ĳ���
			 */
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
		String[] values = paramMap.get(parameter);
		return values == null ? null : values[0];
	}
	
	/**
	 * ��ȡ�������������
	 * 	URL���ӣ�127.0.0.1/photo/index.html?like=book&like=movie&user=root&pwd=123
	 * 	��ȡ like ����������2��Ԫ��{"book","movie"}������
	 * @param parameter
	 * @return
	 */
	public String[] getParameterValues(String parameter){
		return paramMap.get(parameter);
	}
	
	/**
	 * �������2������֮����������������
	 * 1������ POST �����е��������
	 * 		�Ա���ʽ����POST���󣬷�������������۲챨�Ľṹ���������������
	 * 		ע�⣺�������ʵ�����ݻ�ȡ������������� byte[] buffer �������С
	 * 
	 * 2���۲� �ļ��ϴ���ʽ�ύ�������ģ�����Ҫ�����������ӡ�����Ľṹ
	 */
	
	/**
	 * ��Ӳ���
	 * @param name
	 * @param value
	 */
	private void addParameter(String name, String value){
		String[] values = paramMap.get(name);
		if(values==null){
			paramMap.put(name,new String[]{value});
		} else {
			// ֵ��������
			String[] newValues = new String[values.length+1];
			System.arraycopy(values, 0, newValues, 0, values.length);
			newValues[newValues.length-1] = value;
			paramMap.put(name, newValues);
		}
	}
}
