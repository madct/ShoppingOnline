<%@ page language="java" import="java.util.* " import="com.so.model.bookInfo" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//接收getIndexDataServlet的跳转
int shopCartNum=0,num=0;
String welcome= "亲,请登录";
String welout="";
String query="";
if(request.getParameter("query")!=null)
	query=request.getParameter("query");
HttpSession hs=request.getSession(true);
Enumeration<String> en=hs.getAttributeNames();
while(en.hasMoreElements()){
		en.nextElement();
		num++;
	}
if(hs.getAttribute("nickName")!=null && !hs.getAttribute("nickName").equals("")){
	welcome="";
	welout="欢迎你！"+hs.getAttribute("nickName")+" 登出";
	shopCartNum=num-2;
}else
	shopCartNum=num;

//接受数据
ArrayList al=new ArrayList();
if(request.getAttribute("bookInfo")==null)
	response.sendRedirect("index");
else
	al=(ArrayList)request.getAttribute("bookInfo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
  <head>
    <base href="<%=basePath%>">
	<title>ZERO-网购商城</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="ZERO-网购商城">
	<link rel="shortcut icon" href="favicon.ico" />
	<link rel="stylesheet" type="text/css" href="myCss/index.css" >

</head>
	<script type="text/JavaScript" src="jquery/jquery-1.9.1.js" ></script>
	<script type="text/JavaScript" src="js/index.js" charset="GBK"></script>
	<script type='text/javascript'>
function cart(){
		$.ajax({
		type:"post",
		url:"addShopCartServlet",
		data:"info=load",
		success:function(data,textStatus){
			if(textStatus=="success"){
				if(data==0)
					document.getElementById("shopCart").innerHTML=<%=shopCartNum %>;
				else
					document.getElementById("shopCart").innerHTML=data;
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

//添加购物车
function addShopCart(bookId){
	var buyNum=document.getElementById("buy"+bookId).value;

	$.ajax({
		type:"post",
		url:"addShopCartServlet",
		data:"buyNum="+buyNum+"&bookId="+bookId,
		success:function(data,textStatus){
			if(textStatus=="success"){
				document.getElementById("shopCart").innerHTML=data;
				alert("添加成功");
			}
			else 
				alert("ajax链接servlet失败");
		}
	});
	
}


//购买
function buy(bookId){
	var buyNum=document.getElementById("buy"+bookId).value;

	$.ajax({
		type:"post",
		url:"addShopCartServlet",
		data:"buyNum="+buyNum+"&bookId="+bookId,
		success:function(data,textStatus){
			if(textStatus=="success"){
				window.location.href="shoppingCart";
			}
			else 
				alert("ajax链接servlet失败");
		}
	});
	
}


//检查输入
function checkInput(bookId,stock){
	var buyNum=document.getElementById("buy"+bookId).value;
 	if(!/^[0-9]+$/.test(buyNum)){
 		document.getElementById("buy"+bookId).value=1;
 	}else if(stock<buyNum){
 		document.getElementById("buy"+bookId).value=1;
 		alert("购买数量超过该书库存！，请重输！");
 	}
 }
 
 //搜索
 function query(){
 	var queryTxt=document.getElementById("queryTxt").value;
 	window.location.href="index?query="+queryTxt+"";
 }
</script>
<body style="overflow-x: hidden;" onload="cart();" >

<div id="part1">
	<div id="top">
		<div id="topLeft"  >
			<div id="topLeft1"><span id="loginOr" style="color:#F00" onclick="window.location.href='login.jsp'"><%=welcome %></span><span id="Orout" style="color:#F00; " onclick="window.location.href='cleanSession'"><%=welout %></span></div>
            <div id="topLeft2" onclick="window.location.href='signUp.jsp';">免费注册</div>
		</div>
		<div id="topRight" >
			<ul>
				<li onclick="checkLogin();">我的订单</li>
				<li><img src="imgs/shoppingCart.jpg" style="margin-right:5px;"/> <span onclick="window.location.href='shoppingCart'">购物车</span> <span id="shopCart" style="color:#f00;font-size:15px; font-weight:bold"></span></li>
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
		<img src="imgs/logo.png" style="width:100%;height:100%" onclick="window.location.href='index'"/>
	</div>
	<div id="searchAll">
		<div id="txt">商品</div>
		<div><input type="text" id="queryTxt" class="queryText" value="<%=query %>"></div>
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

<div id="line" class="child"></div>

<div id="part3">
  <%for(int i=0;i<al.size();i++){
  	bookInfo bi=(bookInfo)al.get(i);%>
  <div id="goodsAll" >
	<div id="goods" >
		<div id="image">
        	<img src="imgs/books/<%=bi.getBookId() %>.jpg"  style="width:100%; height:100%;"/>
        </div>
		<div id="info">
        	<div id="bookNum" class="goodsInfo">
           	  <span style="font-weight:bold; ">书籍编号：</span><%=bi.getBookId() %>
          </div>
            <div id="bookName" class="goodsInfo">
           	  <span style="font-weight:bold;">书名：</span>《<%=bi.getBookName()%>》
          </div>
            <div id="price" class="goodsInfo">
           	  <span style="font-weight:bold;">售价：</span><%=bi.getPrice()%><span style="font-weight:bold;">RMB</span>
          </div>
            <div id="stock" class="goodsInfo">
           	  <span style="font-weight:bold;">库存量：</span><%=bi.getStock() %><span style="font-weight:bold;">本</span>
          </div>
        </div>
		<div id="about" class="goodsInfo"><span style="font-weight:bold;">简介：</span><%=bi.getIntro() %>
        </div>
     </div>

          <div id="buy">
        	<div class="buy"><span style="font-weight:bold;font-size:18px;">购买数量:</span></div>
            <div><input type="text" maxlength="5" class="num" id="buy<%=bi.getBookId() %>" value=1  oninput="checkInput(<%=bi.getBookId()%>,<%=bi.getStock() %>);"></div>
            <div class="buy1"><span style="font-weight:bold;font-size:18px;">本</span></div>
            <div ><input  id="button1" class="button" type="button" value="购买"  onclick="buy(<%=bi.getBookId() %>);"></div>
            <div ><input  id="button2" class="button" type="button" value="加入购物车" onclick="addShopCart(<%=bi.getBookId() %>);"></div>
          </div>
  </div>
  <%}%>
  <div id="bottom">
		<div id="bottomLine"></div>
   		<div id="statement">本次项目在windows 10专业版(x64位 版本号:10.0.10586版本10586)系统下完成，前端采用div+css制作.所用工具为《Dreanmweaver cs6》版本，测试所用浏览器：IE11(版本:11.306.10586.0)以及CentBrowser(版本 1.9.10.43 (64-bit)(portable) Chromium内核版本号:50.0.2661.87).后端采用jsp+servlet+javascript+java制作.所用工具:编程环境：MyEclipse 2014GA(版本:12.0.0-20131202 Eclipse-Version: 4.3.1.v20130911-1000 Build id: M20130911-1000); 服务器：Tomcat 9.0.0.M6 ; 数据库：SQL Server 2008 R2. 				本网站仅共本人学习使用
   		</div>
	</div>
</div>


</body>
</html>
