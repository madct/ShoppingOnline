package com.so.control;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.so.model.shopCart;
import com.so.model.userBeanHandle;

public class getIndexDataServlet extends HttpServlet {
	

	
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
		String type="";
		String query="";
		if(request.getParameter("type")!=null)
			type=request.getParameter("type");
		if(request.getParameter("query")!=null && !request.getParameter("query").equals(""))
			query=request.getParameter("query");
		request.setAttribute("bookInfo", new userBeanHandle().getBookInfo(type,query));
		request.setAttribute("query", query);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	//���ﳵ���е����ݣ���userName���飬ÿ��userName�Ĺ��ﳵ���ݶ�����һ��ArrayList<shopCart>�У�Ȼ��ȫ������ArrayList����
	
/*	public void init(){	//��дinit����
		ArrayList<ArrayList<shopCart>> al=new ArrayList<ArrayList<shopCart>>();
		ArrayList<shopCart> scAl=new ArrayList<shopCart>();
		try{
			System.out.println("init ������");
			al=(ArrayList<ArrayList<shopCart>>)new userBeanHandle().getShopCart();
			for(int i=0;i<al.size();i++){
				scAl.clear();
				scAl=al.get(i);
				this.getServletContext().setAttribute(scAl.get(0).getUserName(), scAl);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	@SuppressWarnings("unchecked")
/*	public void destroy(){
		try{
			ArrayList<ArrayList<shopCart>> al=new ArrayList<ArrayList<shopCart>>();
			ArrayList<shopCart> temp=new ArrayList<shopCart>();
			Enumeration<String> en=this.getServletContext().getAttributeNames();
			while(en.hasMoreElements()){
				temp.clear();
				
				System.out.println("����-----"+en.nextElement().toString()+"----����");
				//temp=(ArrayList<shopCart>) this.getServletContext().getAttribute(en.nextElement().toString());
				al.add(temp);
			}
			boolean b=new userBeanHandle().updateShopCart(al);
			System.out.println("destroy ������");
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/

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