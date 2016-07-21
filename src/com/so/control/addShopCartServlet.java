package com.so.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.so.model.shopCart;
import com.so.model.userBeanHandle;

public class addShopCartServlet extends HttpServlet {

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
			
			ArrayList<shopCart> al=new ArrayList<shopCart>();
			request.setCharacterEncoding("utf-8");
			String id=null;
			int	hadNum=-1,nick=0;
			HttpSession hs=request.getSession(true);
			String buyNum=request.getParameter("buyNum");
			String bookId=request.getParameter("bookId");
			String nickName=(String) hs.getAttribute("nickName");
			String userName=(String) hs.getAttribute("userName");
			if(nickName!=null && !nickName.equals(""))
				nick=2;
			if(request.getParameter("info")==null || request.getParameter("info").equals("")){ //如果info为空，则表示是增加购物车操作.如果info不为空，则表示是刷新/登录操作.
		
				if(hs.getAttribute(bookId)==null || hs.getAttribute(bookId).toString().equals("")){
					hs.setAttribute(bookId, buyNum);
					
				}else{
					id=hs.getAttribute(bookId).toString();
					hadNum=Integer.parseInt(id)+Integer.parseInt(buyNum);
					hs.setAttribute(bookId,hadNum+"");
					
				}
				
				
				if(nick==2){
					Enumeration<String> en=hs.getAttributeNames();
					ArrayList<String> sAl=new ArrayList<String>();
					while(en.hasMoreElements()){
						sAl.add(en.nextElement().toString());
					}
					ArrayList<shopCart> scAl=new ArrayList<shopCart>();
					for(int i=0;i<sAl.size();i++){
						if(!sAl.get(i).equals("nickName") && !sAl.get(i).equals("userName")){
							shopCart sc=new shopCart();
							sc.setUserName(userName);
							sc.setBookId(Integer.parseInt(sAl.get(i)));
							sc.setNumber(Integer.parseInt(hs.getAttribute(sAl.get(i)).toString()));
							scAl.add(sc);
						}
					}
					new userBeanHandle().updateShopCart(userName, scAl);
				}
			}

			int i=hs.getValueNames().length-nick;
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.write(i+"");
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
