package com.so.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class userBeanHandle {
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	userBean ub=new userBean();
	connectionDb con=new connectionDb();
	
	
	//添加用户
	public boolean addUser(userBean ub){
		boolean b=false;
		try{
			ct=con.getCon();
			ps=ct.prepareStatement("insert into Users(userName,passWd,nickName,sex) values(?,?,?,?)");
			ps.setString(1, ub.getUserName());
			ps.setString(2, ub.getPassWd());
			ps.setString(3, ub.getNickName());
			ps.setString(4, ub.getSex());
			int i=ps.executeUpdate();
			if(i==1)
				b=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	
	//检测注册帐号是否存在
	public boolean checkUserName(String userName){
		boolean b=false;
		
		try {
			ct=con.getCon();
			ps=ct.prepareStatement("select * from Users where userName=?");
			ps.setString(1, userName);
			rs=ps.executeQuery();

			if(rs.next()){
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	
	
	//检测昵称是否存在
	public boolean checkNickName(String NickName){
		boolean b=false;
		
		try {
			ct=con.getCon();
			ps=ct.prepareStatement("select * from Users where nickName=?");
			ps.setString(1, NickName);
			rs=ps.executeQuery();
			if(rs.next()){
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	
	
	//check user login
	public boolean checkLogin(String userName,String passWd){
		String temp=null;
		boolean b=false;
		try{
			ct=con.getCon();
			ps=ct.prepareStatement("select  passwd from Users where userName=?");
			ps.setString(1, userName);
			rs=ps.executeQuery();
			if(rs.next())
				temp=rs.getString(1);
			if(temp.equals(passWd))
				b=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	
	//得到用户的昵称
	public String getNickName(String userName){
		String nickName=null;
		try{
			ct=con.getCon();
			ps=ct.prepareStatement("select  nickName from Users where userName=?");
			ps.setString(1, userName);
			rs=ps.executeQuery();
			if(rs.next())
				nickName=rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		return nickName;
	}
	
	//得到用户的ID
	public String getUserId(String userName){
		String userId=null;
		try{
			ct=con.getCon();
			ps=ct.prepareStatement("select  userId from Users where userName=?");
			ps.setString(1, userName);
			rs=ps.executeQuery();
			if(rs.next())
				userId=rs.getInt(1)+"";
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		return userId;
	}
	
	//get bookInfo
	public ArrayList<bookInfo> getBookInfo(String type,String query){
		ArrayList<bookInfo> al=new ArrayList<bookInfo>();
		String sql=null;
		if(query==null || query.equals("")){
			if(type!=null && !type.equals(""))
				sql="select * from Books where type='"+type+"'";
			else
				sql="select * from Books";
		}else
			sql="select * from Books where bookName like '%"+query+"%'";
		try{
			ct=con.getCon();
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				bookInfo bi=new bookInfo();
				bi.setBookId(rs.getInt(1));
				bi.setBookName(rs.getString(2));
				bi.setPrice(rs.getBigDecimal(3));
				bi.setStock(rs.getInt(4));
				bi.setIntro(rs.getString(5));
				al.add(bi);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}	
		return al;	
	}
	
	//get users'shopCart
	public ArrayList<shopCart> getShopCart(String userName){
		ArrayList<shopCart> al=new ArrayList<shopCart>();
		try{
			ct=con.getCon();
			ps=ct.prepareStatement("select * from ShopCart where userName='"+userName+"'");
			rs=ps.executeQuery();
			while(rs.next()){				
				shopCart sc=new shopCart();
				sc.setUserName(rs.getString(1));
				sc.setBookId(rs.getInt(2));
				sc.setNumber(rs.getInt(3));
				al.add(sc);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		return al;
	}
	
	
	//update users'shopCart from loginCheckServlet
	public void updateShopCart(String userName,ArrayList<shopCart> al){
		int x=0;
		try {
			ct=con.getCon();
			for(int i=0;i<al.size();i++){
				ps=ct.prepareStatement("select * from ShopCart where userName=? and bookId=?");
				ps.setString(1, userName);
				ps.setInt(2, al.get(i).getBookId());
				rs=ps.executeQuery();
				if(rs.next()){
					ps=ct.prepareStatement("update ShopCart set number=? where userName=? and bookId=?");
					ps.setInt(1, al.get(i).getNumber());
					ps.setString(2, userName);
					ps.setInt(3, al.get(i).getBookId());
				}else{
					ps=ct.prepareStatement("insert into ShopCart values(?,?,?)");
					ps.setString(1, userName);
					ps.setInt(2, al.get(i).getBookId());
					ps.setInt(3, al.get(i).getNumber());
				}
				if(ps.executeUpdate()==1)
					x++;
			}
			if(x!=al.size())
				System.out.println("loginCheckServlet 中，更新用户购物车的数据库失败");
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
	}
	
	
	//get shopCartBookInfo(ArrayList)
	public ArrayList<bookInfo> getShopCartBookInfo(ArrayList<String> al){
		ArrayList<bookInfo> alBi=new ArrayList<bookInfo>();
		try{
			ct=con.getCon();
			for(int i=0;i<al.size();i++){
				ps=ct.prepareStatement("select  * from Books where bookId="+al.get(i)+"");
				rs=ps.executeQuery();
				while(rs.next()){
					bookInfo bi=new bookInfo();
					bi.setBookId(rs.getInt(1));
					bi.setBookName(rs.getString(2));
					bi.setPrice(rs.getBigDecimal(3));
					alBi.add(bi);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}	
		return alBi;	
	}
	
	
	//getOneBookInfo
	public bookInfo getOneBookInfo(String bookId){
		bookInfo bi=new bookInfo();
		try{
			ct=con.getCon();
			ps=ct.prepareStatement("select  * from Books where bookId="+bookId+"");
			rs=ps.executeQuery();
			while(rs.next()){
				bi.setBookId(rs.getInt(1));
				bi.setBookName(rs.getString(2));
				bi.setPrice(rs.getBigDecimal(3));
				bi.setStock(rs.getInt(4));
				bi.setIntro(rs.getString(5));
				bi.setType(rs.getString(6));
			}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		this.close();
	}	
	return bi;	
}
	
	
	
	//delete one or all of user's shopCart 
	public boolean deleteShopCart(String userName,String bookId,int type){
		boolean b=true;
		String sql=null;
		
		if(type==1){
			sql="delete from ShopCart where userName='"+userName+"' and bookId="+bookId+"";
		}else
			sql="delete from ShopCart where userName='"+userName+"'";
		try{
			ct=con.getCon();
			ps=ct.prepareStatement(sql);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			b=false;
		}finally{
			this.close();
		}
		return b;
	}
	
	
	//insert userOrder
	public void addUserOrder(ArrayList<userOrder> al){
		int count=0;
		try {
			ct=con.getCon();
			ps=ct.prepareStatement("insert into UserOrder(orderNum,userName,bookId,number,money,time) values(?,?,?,?,?,?)");
			for(int i=0;i<al.size();i++){
				userOrder uo=al.get(i);
				ps.setString(1, uo.getOrderNum());
				ps.setString(2, uo.getUserName());
				ps.setInt(3, uo.getBookId());
				ps.setInt(4, uo.getNumber());
				ps.setBigDecimal(5, uo.getMoney());
				ps.setString(6, uo.getTime());
				ps.addBatch();
				count++;

			}
			ps.executeBatch();
			if(count!=al.size())
				System.out.println("submitOrderServlet 中，添加用户订单失败");
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
	}
	
	
	//get userOrder(ArrayList)
		public ArrayList<ArrayList<userOrder>> getUserOrder(String userName){
			ArrayList<ArrayList<userOrder>> al=new ArrayList<ArrayList<userOrder>>(); //放置分组后的订单集合
			ArrayList<userOrder> alUo=new ArrayList<userOrder>();  //放置数据库中该userName的全部订单信息
			ArrayList<String> sAl=new ArrayList<String>();		//放置orderNum的String
			try{
				ct=con.getCon();
				ps=ct.prepareStatement("select distinct orderNum from UserOrder where userName='"+userName+"' order by orderNum desc");
				rs=ps.executeQuery();
				while(rs.next()){
					sAl.add(rs.getString(1));
				}
			for(int i=0;i<sAl.size();i++){
				alUo=new ArrayList<userOrder>();

				ps=ct.prepareStatement("select * from UserOrder where userName='"+userName+"' and orderNum='"+sAl.get(i)+"'");
				rs=ps.executeQuery();
				while(rs.next()){
					userOrder uo=new userOrder();
					uo.setOrderNum(rs.getString(1));
					uo.setUserName(rs.getString(2));
					uo.setBookId(rs.getInt(3));
					uo.setNumber(rs.getInt(4));
					uo.setMoney(rs.getBigDecimal(5));
					uo.setTime(rs.getString(6));
					alUo.add(uo);
				
				}
				al.add(alUo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
				this.close();
			}	
			return al;	
		}
	
	//关闭数据库资源
	public void close(){	
		try{
			if(rs!=null)
				rs.close();
			if(ps!=null)
				ps.close();
			if(ct!=null)
				ct.close();
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
}
