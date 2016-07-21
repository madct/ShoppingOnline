package com.so.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubmitCheckServlet extends HttpServlet {

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
			response.sendRedirect("index");
		else{
			response.setCharacterEncoding("UTF-8");
			HttpSession hs=request.getSession(true);
			String nickName=(String) hs.getAttribute("nickName");
			int nick=0;
			if(nickName!=null && !nickName.equals(""))
				nick=2;
			int countShop=0;
			Enumeration<String> en=request.getSession(true).getAttributeNames();
			while(en.hasMoreElements()){
				en.nextElement();
				countShop++;
			}
			PrintWriter out=response.getWriter();
			String info="";
			if(nick==0)
				info="您还未登录！请先登录！";
			else if(countShop-nick<=0)
				info="您未选择任何商品！请先添加！";
			else
				info="go";
			out.write(info);
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
