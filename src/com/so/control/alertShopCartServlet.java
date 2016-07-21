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
import com.so.model.bookInfo;

public class alertShopCartServlet extends HttpServlet {

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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		HttpSession hs=request.getSession(true);
		String bookId=request.getParameter("bookId");
		String buyNum=request.getParameter("buyNum");
		int intNum=Integer.parseInt(buyNum);
		userBeanHandle ubh=new userBeanHandle();
		bookInfo bi=ubh.getOneBookInfo(bookId);
		int stock=bi.getStock();
		if(stock>=intNum){
		hs.setAttribute(bookId, buyNum); //将修改后的购买书本数量更新到session
		Enumeration<String> en=hs.getAttributeNames();
		ArrayList<String> al=new ArrayList<String>();
		while(en.hasMoreElements()){
			al.add(en.nextElement().toString());
		}
		String userName=null;
		int number=0;
		double money=0;
		double price=0;
		boolean b=false;
		for(int i=0;i<al.size();i++){
			if(!al.get(i).equals("nickName") && !al.get(i).equals("userName")){
				number+=Integer.parseInt((String)hs.getAttribute(al.get(i)));
				//从session中得到相应bookId的number，再从数据库中得到该ID书的价格。得积
				price=new userBeanHandle().getOneBookInfo(al.get(i)).getPrice().doubleValue();
				money+=Integer.parseInt((String)hs.getAttribute(al.get(i)))*price;
			}else
			{
				b=true;
				userName=(String)hs.getAttribute("userName");
			}
		}
		PrintWriter out = response.getWriter();
		out.println("{\"allNumber\":\""+number+"\",\"money\":\""+(double)Math.round(money*100)/100+"\"}");
		out.flush();
		out.close();
		
		//更新数据库
		if(b && userName!=null && !userName.equals("")){
			ArrayList<shopCart> scAl=new ArrayList<shopCart>();
			shopCart sc=new shopCart();
			sc.setUserName(userName);
			sc.setBookId(Integer.parseInt(bookId));
			sc.setNumber(Integer.parseInt(buyNum));
			scAl.add(sc);
			new userBeanHandle().updateShopCart(userName, scAl);
		}
		}else{
			PrintWriter out = response.getWriter();
			out.println("{\"info\":\"购买数量超过该书库存，请重输！\"}");
			out.flush();
			out.close();	
		}
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
