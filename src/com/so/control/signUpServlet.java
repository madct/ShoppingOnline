package com.so.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.so.model.userBean;
import com.so.model.userBeanHandle;

public class signUpServlet extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		String userName=request.getParameter("userName");
		String passWd=request.getParameter("passWd");
		String nickName=request.getParameter("nickName");
		String sex=request.getParameter("sex");
		userBean ub=new userBean();
		ub.setUserName(userName);
		ub.setPassWd(passWd);
		ub.setNickName(nickName);
		ub.setSex(sex);
		if(new userBeanHandle().addUser(ub)){
			request.setAttribute("info", "¹§Ï²Äã£¡ ×¢²á³É¹¦£¡");
			request.setAttribute("img", "smile.png");
			request.setAttribute("go", "index.jsp");
		}else{
			request.setAttribute("info", "ºÜÒÅº¶£¡ ×¢²áÊ§°Ü£¡");
			request.setAttribute("img", "cry.jpg");
			request.setAttribute("go", "signUp.jsp");
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
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
	public void destroy(){
		super.destroy();
	}
}
