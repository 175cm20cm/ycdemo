package com.yc.http.tomcat.v3;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * ����Servlet�Ĺ���
 * 1���̳� �� HttpServlet
 * 2����д�� doXXX����
 * 3������: URL(ע�⣬web.xml)
 */
public class HelloServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		out.print("hello "+name+"!");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
	
	

}
