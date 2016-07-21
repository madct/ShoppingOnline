package com.so.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.so.model.shopCart;
import com.so.model.userBeanHandle;
import com.so.model.userOrder;

public class submitOrderServlet extends HttpServlet {

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
			String userName=(String) hs.getAttribute("userName");
			String nickName=(String) hs.getAttribute("nickName");
			response.setCharacterEncoding("utf-8");
			request.setAttribute("info", "订单提交成功！");
			request.setAttribute("img", "smile.png");
			request.setAttribute("go", "index");
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			SimpleDateFormat dftime = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");//设置日期格式
			String dfOrderNum=df.format(new Date()); //获取时间
			Date dt2=null;
			try {
				dt2 = df.parse(dfOrderNum);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String time=dftime.format(dt2);
				
			String orderNum=new userBeanHandle().getUserId(userName)+dfOrderNum;
			ArrayList<userOrder> al=new ArrayList<userOrder>();
			Enumeration<String> en=hs.getAttributeNames();
			ArrayList<String> sAl=new ArrayList<String>();
			while(en.hasMoreElements()){
				sAl.add(en.nextElement().toString());
			}
			
			for(int i=0;i<sAl.size();i++){
				if(!sAl.get(i).equals("nickName") && !sAl.get(i).equals("userName")){
					userOrder uo=new userOrder();
					uo.setOrderNum(orderNum);
					uo.setUserName(userName);
					uo.setBookId(Integer.parseInt(sAl.get(i)));
					uo.setNumber(Integer.parseInt(hs.getAttribute(sAl.get(i)).toString()));
					double tempM=new userBeanHandle().getOneBookInfo(sAl.get(i)).getPrice().doubleValue()*Integer.parseInt(hs.getAttribute(sAl.get(i)).toString());
					BigDecimal money= new BigDecimal(tempM).setScale(2, BigDecimal.ROUND_HALF_UP);
					uo.setMoney(money);
					uo.setTime(time);
					al.add(uo);
				}
			}
			new userBeanHandle().addUserOrder(al);  //将订单添加到数据库
			
			hs.invalidate();		//销毁session
			
			if(userName!=null && !userName.equals("") && nickName!=null && !nickName.equals("")){
				request.getSession(true).setAttribute("userName", userName);
				request.getSession(true).setAttribute("nickName", nickName);
				if(!new userBeanHandle().deleteShopCart(userName, "", 9)){
					System.out.println("deleteShopCartServlet中的第46行返回false");	
				}
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

}
