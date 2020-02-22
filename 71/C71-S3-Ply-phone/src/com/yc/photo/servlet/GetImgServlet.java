package com.yc.photo.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet �����Ĳ��裺
 * 	1���̳� HttpServlet
 * 	2��ʵ�� doXXX
 * 	3������ ע�⡢web.xml
 * 
 * Ӧ�������Ķ��� ServletContext
 * 1���������ڳ����������������
 * 2���������
 */
@WebServlet("/getImg.do")
public class GetImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		/**
		 * 1����ȡ�����е�ͼƬ�ļ�
		 * 2��ѭ������html����
		 */
		String webpath = "/images/fulls";
		// ��ȡӦ�������Ķ���
		ServletContext application = this.getServletContext();
		// ��web·��ת�ɴ���·��
		String diskpath = application.getRealPath(webpath);
		// �����ļ�����
		File dir = new File(diskpath);
		System.out.println(dir);
		// ��ȡ�ļ��������е��ļ�
		String[] filenameArray = dir.list();
		String s = "<article class=\"thumb\">\n" +
				"	<a href=\"images/fulls/%s\" class=\"image\">\n" +
				"		<img src=\"images/fulls/%s\" alt=\"\" />\n" +
				"	</a>\n" +
				"	<h2>��Ƭ��</h2>\n" +
				"	<p>��Ƭ����</p>\n" +
				"</article>";
		// 1   StringBuilder ���߳�Ч�ʸ�     2  StringBuffer
		for(String filename : filenameArray) {
			String ss = String.format(s, filename, filename);
			response.getWriter().append(ss);
		}
		
	}

}
