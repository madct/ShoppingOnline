package com.so.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.so.model.userBeanHandle;

public class deleteShopCartServlet extends HttpServlet {

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
		HttpSession hs=request.getSession(true);
		response.setContentType("text/html");
		String bookId=request.getParameter("bookId");
		String userName=(String) hs.getAttribute("userName");
		String nickName=(String) hs.getAttribute("nickName");
		int nick=0;
		if(userName!=null && !userName.equals("")){
			nick=2;
		}
		if(bookId!=null && !bookId.equals("")){
			hs.removeAttribute(bookId);
			if(userName!=null && !userName.equals("") && nickName!=null && !nickName.equals("")){
				System.out.println("我进来了");
				if(!new userBeanHandle().deleteShopCart(userName, bookId, 1)){
					System.out.println("deleteShopCartServlet中的第36行返回false");	
				}
			}
		}else{
			hs.invalidate();
			if(userName!=null && !userName.equals("") && nickName!=null && !nickName.equals("")){
				System.out.println("我进来了2");
				request.getSession(true).setAttribute("userName", userName);
				request.getSession(true).setAttribute("nickName", nickName);
				if(!new userBeanHandle().deleteShopCart(userName, "", 9)){
					System.out.println("deleteShopCartServlet中的第46行返回false");	
				}
			}
		}	
		
		int countShop=0;
		Enumeration<String> en=request.getSession(true).getAttributeNames();
		while(en.hasMoreElements()){
			en.nextElement();
			countShop++;
		}
		countShop-=nick;
		PrintWriter out = response.getWriter();
		out.println(countShop);
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
