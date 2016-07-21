<%@ page language="java" import="java.util.*" import="com.so.model.bookInfo" import="java.util.Enumeration" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

if(request.getAttribute("info")==null)
	response.sendRedirect("shoppingCart");
	
HttpSession hs=request.getSession(true);
Enumeration<String> en=hs.getAttributeNames();

ArrayList<bookInfo> al=(ArrayList<bookInfo>)request.getAttribute("sCBookInfo");

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


function checkInput(bookId,usedNum){
	var test=usedNum;
	var buyNum=document.getElementById("buyNum"+bookId).value;
 	if(!/^[0-9]+$/.test(buyNum)){
 		document.getElementById("buyNum"+bookId).value=test
 	}
 }
 
 function changeBuyNum(bookId){
 		var buyNum=document.getElementById("buyNum"+bookId).value;
 		$.ajax({
			type:"post",
			url:"alertShopCart",
			data:"bookId="+bookId+"&buyNum="+buyNum,
			dataType:"json",
			success:function(data){
				if(data.info){
					alert(data.info);
				}else{
				document.getElementById("allNumber").innerHTML=data.allNumber;
				document.getElementById("sumMoney").innerHTML=data.money;
				}
			},
			error:function(){
				alert("ajax链接servlet失败");
			}
		
		});
} 

 function deleteOne(bookId){
 		if(window.confirm('确认删除？')){
 		var bigCartBook=document.getElementById("bigCartBook"+bookId);
 		$.ajax({
			type:"post",
			url:"deleteShopCart",
			data:"bookId="+bookId,
			success:function(data){
				bigCartBook.parentNode.removeChild(bigCartBook);
				document.getElementById("shopCart2").innerHTML=data;
				if(data==0)
					window.location.href="shoppingCart";
			},
			error:function(){
				alert("链接失败(shoppingCart)");
			}
		
		});
		return true;
	}else
		return false;
} 

 function  cleanShopCart(){
 		if(window.confirm('确认清空？')){
 		$.ajax({
			type:"post",
			url:"deleteShopCart",
			success:function(data){
				window.location.href="shoppingCart";
			},
			error:function(){
				alert("链接失败(shoppingCart)");
			}
		
		});
		return true;
	}else
		return false;
}


 function  checkSubmitOrder(){
 		if(window.confirm('确认提交？')){
		$.ajax({
			type:"post",
			url:"SubmitCheck",
			success:function(data){
				if(data=="go"){
					window.location.href="submitOrder";
				}else
					alert(data);	
			},
			error:function(){
				alert("链接失败(shoppingCart)");
			}
		
		});
		return true;
	}else
		return false;
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

<body style="overflow-x: hidden; padding-bottom:50px;" onload="cart();">
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
<div id="shopCart">购物车</div>
    	<div id="cartHead">
			<ul>
				<li class="cartHead">书籍编号</li>
       			<li>|</li>
				<li class="cartHead">书名</li>
       	 		<li>|</li>
				<li class="cartHead">单价</li>
        		<li>|</li>
				<li class="cartHead">订购数量</li>
                <li>|</li>
                <li class="cartHead">操作</li>
			</ul>
		</div>
        
        <%
        
        double money=0;
        if(al!=null && al.size()>0 && en.hasMoreElements()){ 
        	for(int i=0;i<al.size();i++){
        %>
        <div id="bigCartBook<%=al.get(i).getBookId() %>" class="bigCartBook">
			<ul>
				<li class="cartBook" ><%=al.get(i).getBookId()+"" %></li>
       			<li>|</li>
				<li class="cartBook" style="line-height:20px;">《<%=al.get(i).getBookName() %>》</li>
       	 		<li>|</li>
				<li class="cartBook"><%=al.get(i).getPrice() %></li>
        		<li>|</li>
				<li class="cartBook" ><input id="buyNum<%=al.get(i).getBookId()%>" type="text" maxlength="5" class="cartBook" value="<%=hs.getAttribute(al.get(i).getBookId()+"") %>" 
				  oninput="checkInput(<%=al.get(i).getBookId()%>,<%=hs.getAttribute(al.get(i).getBookId()+"") %>);" style="background-color:#FFF; font-size:18px;"></li>
                <li>|</li>
                <li class="cartBook"><input class="alertButton" type="button" value="修改" onclick="changeBuyNum(<%=al.get(i).getBookId() %>);"><input class="alertButton" type="button" value="删除" onclick="return deleteOne(<%=al.get(i).getBookId() %>);"></li>
			</ul>
		</div>
		
<%			
			
			int x=al.get(i).getBookId();
			String sx=x+"";
			String temp=hs.getAttribute(sx).toString();
			number+=Integer.parseInt(temp);
			money+=(al.get(i).getPrice().doubleValue()*Integer.parseInt(hs.getAttribute(al.get(i).getBookId()+"").toString()));
			
	}}else{
	
	%><div id="shopCart">暂无商品<br><span style="font-size:16px;line-height:30px;color:#020202;">点击<a href="index">此处</a>前去购物</span></div><%

} %>


<div id="line" style="margin-top:100px;"></div>
<div id="jiesuan">
	<div id="clear" onclick="return cleanShopCart();">清空购物车</div>
	<div id="zongji">
    	购买总数量：<span id="allNumber"><%=number %></span>本 ;
    </div>
    <div id="zongji">
        价格总计：￥<span id="sumMoney"><%=(double)Math.round(money*100)/100 %></span>
    </div>
    <div id="add">
        点击<a href="index">此处</a>继续购物
    </div>
    <div id="buttonJieSuan" onclick="return checkSubmitOrder();">
        提交订单
    </div>
</div>

</body>

</html>
