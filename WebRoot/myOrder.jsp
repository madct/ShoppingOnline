<%@ page language="java" import="java.util.*" import="com.so.model.userOrder" import="com.so.model.userBeanHandle" import="java.util.Enumeration" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

if(request.getHeader("REFERER")==null || request.getHeader("REFERER").equals(""))
			response.sendRedirect("index");

HttpSession hs=request.getSession(true);
Enumeration<String> en=hs.getAttributeNames();

ArrayList<ArrayList<userOrder>> al=(ArrayList<ArrayList<userOrder>>)request.getAttribute("myOrder");
ArrayList<userOrder> temp=new ArrayList<userOrder>();
String welcome="亲，请登录！";
String welout="";
if(hs.getAttribute("nickName")!=null && !hs.getAttribute("nickName").equals("")){
	welcome="";
	welout="欢迎你！"+hs.getAttribute("nickName")+" 登出";
}

int number=0;

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="myCss/index.css" rel="stylesheet" type="text/css"/>
	<title>ZERO-网购商城</title>
	<link rel="shortcut icon" href="favicon.ico" />
</head>

	<script type="text/JavaScript" src="jquery/jquery-1.9.1.js" ></script>
	<script type='text/javascript' >
function cart(){
		$.ajax({
		type:"post",
		url:"addShopCartServlet",
		data:"info=load",
		success:function(data,textStatus){
			if(textStatus=="success"){
				document.getElementById("shopCart2").innerHTML=data;
			}
			else 
				alert("ajax链接servlet失败");
		}
	});
}


//点击《我的订单》时，检查是否登录
function checkLogin(){
		$.ajax({
		type:"post",
		url:"orderCheckLogin",
		success:function(data){
				if(data=="0")
					window.location.href="getOrder";
				else
					alert("您还未登录，请先登录！");
		},	
		error:function(){
				alert("ajax链接servlet失败");
		}
	});
}

 //搜索
 function query(){
 	var queryTxt=document.getElementById("queryTxt").value;
 	window.location.href="index?query="+queryTxt+"";
 }
 
</script>

<body style="overflow-x: hidden; padding-bottom:50px;" onload="cart();" >
<div id="part1">
	<div id="top">
		<div id="topLeft"  >
			<div id="topLeft1"><span id="loginOr" style="color:#F00" onclick="window.location.href='login.jsp'"><%=welcome %></span><span id="Orout" style="color:#F00; " onclick="window.location.href='cleanSession'"><%=welout %></span></div>
            <div id="topLeft2" onclick="window.location.href='signUp.jsp'">免费注册</div>
		</div>
		<div id="topRight" >
			<ul>
				<li onclick="checkLogin();">我的订单</li>
				<li><img src="imgs/shoppingCart.jpg" style="margin-right:5px;"/> <span onclick="window.location.href='shoppingCart'">购物车</span> <span id="shopCart2" style="color:#f00;font-size:15px; font-weight:bold"></span></li>
				<li onclick="alert('联系邮箱为：406727293@qq.com');">联系客服</li>
			</ul>
		</div>
    </div>
    <div id="picture">
			<img src="imgs/头广告.png"; style="width:100%" height="50"/>
	</div>
</div>

<div id="part2">
	<div id="logo">
		<img src="imgs/logo.png" style="width:100%" height="100%" onclick="window.location.href='index'" />
	</div>
	<div id="searchAll">
		<div id="txt">商品</div>
		<div><input type="text" id="queryTxt" class="queryText"></div>
	</div>
	<div id="button" onclick="query();">搜索</div>
</div>

<div id="menu">
	<ul>
		<li class="menu" onclick="window.location.href='index'">全部书籍</li>
        <li>|</li>
		<li class="menu" onclick="window.location.href='index?type=chinese'">中文图书</li>
        <li>|</li>
		<li class="menu" onclick="window.location.href='index?type=foreign'">进口图书</li>
        <li>|</li>
		<li class="menu" onclick="window.location.href='index?type=education'">教材教辅</li>
        <li>|</li>
        <li class="menu" onclick="window.location.href='index?type=art'">文学艺术</li>
        <li>|</li>
       	<li class="menu" onclick="window.location.href='index?type=humanity'">人文社科</li>
        <li>|</li>
     	<li class="menu" onclick="window.location.href='index?type=economy'">经济管理</li>
        <li>|</li>
     	<li class="menu" onclick="window.location.href='index?type=technology'">科技生活</li>
        <li>|</li>
     	<li class="menu" onclick="window.location.href='index?type=encourage'">励志成功</li>
	</ul>
</div>

<div id="line"></div>
<div id="shopCart">我的订单</div>
    	<div id="orderHead">
        	<div class="orderHead">
            订单号
            </div>
            <div  class="orderHead">
            书名
            </div>
            <div  class="orderHead">
            订购数量
            </div>
             <div  class="orderHead">
            金额
            </div>
             <div class="orderHead" >
            下单时间
            </div>
</div>
<%
double sumMoney=0;
int buyNum=0;
if(al!=null && al.size()>0){
        
        for(int i=0;i<al.size();i++){
       		temp=new ArrayList<userOrder>();
	    	temp.addAll(al.get(i));%>
         <div id="orderAll" style="height:<%=(temp.size()+1)*40 %>px; ">
        <div id="order" style="height:<%=(temp.size()+1)*40 %>px">
        	<div id="orderNum" style="height:<%=(temp.size()+1)*40 %>px;line-height:<%=(temp.size()+1)*40 %>px; ">
            <%=temp.get(0).getOrderNum() %>
            </div>
          <%
			double oMoney=0;
	    	for(int j=0;j<temp.size();j++){
		   		userOrder uo=new userOrder();
		    	uo=temp.get(j);
		    	buyNum+=uo.getNumber();
	        	oMoney+=uo.getMoney().doubleValue();
	        	sumMoney+=uo.getMoney().doubleValue();
          %>
          <div class="order3Info">
            <div  class="orderInfo">
            <%=new userBeanHandle().getOneBookInfo(uo.getBookId()+"").getBookName() %>
            </div>
            <div  class="orderInfo">
          <%=uo.getNumber() %>
            </div>
             <div  class="orderInfo">
            <%=uo.getMoney().doubleValue() %>
            </div>
		</div>
            <%} %>

            <div  id="orderMoney">
            金额总计: <%=(double)Math.round(oMoney*100)/100 %>
            </div>
   </div>
            <div class="orderTime" style="height:<%=(temp.size()+1)*40 %>px; line-height:40px;">
            <%=temp.get(0).getTime() %>
            </div>
</div>
	<%}
	}else
	{ %>
	<div id="shopCart">暂无订单<br><span style="font-size:16px;line-height:30px;color:#020202;">点击<a href="index">此处</a>前去购物</span></div>
<%} %>



<div id="line" style="margin-top:100px;"></div>
<div id="jiesuan">
	<div id="clear" onclick="window.location.href='cleanSession'">退出登录</div>
	<div id="zongji">
    	购买总数量：<span id="allNumber"><%=buyNum %></span>本 ;
    </div>
    <div id="zongji">
        费用总计：￥<span id="sumMoney"><%=(double)Math.round(sumMoney*100)/100 %></span>
    </div>
    <div id="buttonJieSuan" onclick="window.location.href='index'">
        返回上页
    </div>
</div>

</body>

</html>
