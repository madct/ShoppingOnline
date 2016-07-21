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

public class loginCheckServlet extends HttpServlet {

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
			response.sendRedirect("login.jsp");
		else{
		ArrayList<shopCart> al=new ArrayList<shopCart>();
		ArrayList<String> temp=new ArrayList<String>();
		request.setCharacterEncoding("utf-8");
		String userName=request.getParameter("userName");
		String passWd=request.getParameter("passWd");
		PrintWriter out=response.getWriter();
		if(new userBeanHandle().checkUserName(userName) && new userBeanHandle().checkLogin(userName, passWd)){
			al=new userBeanHandle().getShopCart(userName);
			if(request.getSession(true).getAttribute("userName")!=null && !((String)request.getSession(true).getAttribute("userName")).equals(userName)){
				request.getSession(true).invalidate();
			}
			HttpSession hs=request.getSession(true);
			Enumeration<String> en=hs.getAttributeNames();
			while(en.hasMoreElements()){
				temp.add(en.nextElement().toString());
			}
			
			
			if(temp.size()>0 && al.size()>0){		//将从数据库取出的用户的购物车数据，更新到session中
				for(int i=0;i<al.size();i++){
					shopCart sc=al.get(i);
					boolean b=true;
					for(int j=0;j<temp.size() && b;j++){
						
						if(temp.get(j).equals(sc.getBookId()+"")){
							hs.setAttribute(temp.get(j), (Integer.parseInt(hs.getAttribute(temp.get(j)).toString())+sc.getNumber())+"");
							b=false;

						}
					}
					if(b)
						hs.setAttribute(sc.getBookId()+"",sc.getNumber()+"");
				}
				
				//把更新后的session中的数据写入数据库
				
				al.clear();
				temp.clear();
				Enumeration<String> en2=hs.getAttributeNames();
				while(en2.hasMoreElements()){
					temp.add(en2.nextElement().toString());
				}
				for(int i=0;i<temp.size();i++){
					if(!temp.get(i).equals("userName") && !temp.get(i).equals("nickName")){
						shopCart sc=new shopCart();
						sc.setUserName(userName);
						sc.setBookId(Integer.parseInt(temp.get(i)));
						sc.setNumber(Integer.parseInt((String) hs.getAttribute(temp.get(i))));
						al.add(sc);
				}
			}
				new userBeanHandle().updateShopCart(userName,al);
				
			}else{
				if(temp.size()<=0 && al.size()>0){
					for(int i=0;i<al.size();i++){
						shopCart sc=al.get(i);
						hs.setAttribute(sc.getBookId()+"",sc.getNumber()+"");
					}
				}
			}
			
			hs.setAttribute("userName", userName);
			hs.setAttribute("nickName", new userBeanHandle().getNickName(userName));
		}
		else
			out.write("false");
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
