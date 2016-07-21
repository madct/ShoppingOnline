<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//接收来自signUpServlet.java , 并跳转到 index.jsp页面
String info=(String)request.getAttribute("info");
String img=(String)request.getAttribute("img");
String go=(String)request.getAttribute("go");

if(request.getHeader("REFERER")==null || request.getHeader("REFERER").equals(""))
			response.sendRedirect("index");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>操作结果</title>
    <link rel="shortcut icon" href="favicon.ico" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- <meta http-equiv="refresh" content ="3;url=./index.jsp">  页面自动跳转 -->
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
    
<script type='text/javascript'>
var num=3;
	function calc(){
		if(num>0){       
			document.getElementById("second").innerHTML=num; 
			num--;      
		}else{
			location.href="./<%=go%>";
		}
		setTimeout("calc()",1000);
	}	

</script>

<body onload="calc();">
<div style=" text-align:center;width:1100px; height:200px; margin-left:auto; margin-right:auto; margin-top:200px;">
	<div style="width:700px; height:200px; margin-left:auto; margin-right:auto;">
    	<div style="width:200px; height:200px; float:left;"><img src="imgs/<%=img %>" /></div>
    	<div style=" width:500px;line-height:170px; text-align:center; float:left; font-size:45px;"><%=info %></div> 
        <div style="float:left; width:500px;height:30px;line-height:30px; color: #999">页面将在<span id="second" style="color:#30F;">3</span>秒内跳转！如果浏览器没有跳转，请点击<a href="index.jsp">此处</a></div>
    </div>
   </div> 
</body>
</html>
