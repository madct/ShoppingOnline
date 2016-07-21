package com.so.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.so.model.userBeanHandle;

public class signCheckServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getHeader("REFERER")==null || request.getHeader("REFERER").equals(""))
			response.sendRedirect("signUp.jsp");
		else{
		userBeanHandle ubh=new userBeanHandle();
		String userName=request.getParameter("userName");
		String nickName=request.getParameter("nickName");
		PrintWriter out = response.getWriter();
		if(nickName!=null && !nickName.equals(""))
			out.write(ubh.checkNickName(nickName)+"");
		if(userName!=null && !userName.equals(""))
			out.write(ubh.checkUserName(userName)+"");
		out.flush();
		out.close();
	}
}
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
