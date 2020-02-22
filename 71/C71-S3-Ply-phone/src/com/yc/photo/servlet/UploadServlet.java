package com.yc.photo.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part = request.getPart("file");
		String filename = part.getSubmittedFileName();
		
		/**
		 * �ϴ�ͼƬ�� չʾĿ¼��
		 */
		String webpath = "/images/fulls";
		// ��ȡӦ�������Ķ���
		ServletContext application = this.getServletContext();
		// ��web·��ת�ɴ���·��
		String diskpath = application.getRealPath(webpath);
		
		part.write(diskpath + "/" +filename);
		
		response.getWriter().append("success");
		
	}

}
