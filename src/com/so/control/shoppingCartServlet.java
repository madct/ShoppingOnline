package com.so.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.so.model.userBeanHandle;

public class shoppingCartServlet extends HttpServlet {

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

//		if(request.getHeader("REFERER")==null && request.getHeader("REFERER").equals(""))
//			response.sendRedirect("login.jsp");
//		else{
			ArrayList<String> al=new ArrayList<String>();
			ArrayList<String> sAN=new ArrayList<String>();  //sAN = sessionAttributeNames
			Enumeration<String> en=request.getSession(true).getAttributeNames();
			while(en.hasMoreElements()){
				sAN.add(en.nextElement());
			}
			if(sAN.size()>0){
				for(int i=0;i<sAN.size();i++){
					if(!sAN.get(i).equals("userName") && !sAN.get(i).equals("nickName")){
						al.add(sAN.get(i));
					}
				}
				request.setAttribute("sCBookInfo", new userBeanHandle().getShopCartBookInfo(al));
			}
			request.setAttribute("info", "1");
			request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
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
