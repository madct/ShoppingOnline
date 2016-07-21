<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String display="none";
if(request.getParameter("info")!=null && !(request.getParameter("info").equals("")))
	display="block";
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆界面-ZERO网上书城</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="myCss/login.css">
	<link rel="shortcut icon" href="favicon.ico" />
  </head>
  	<script type="text/JavaScript" src="jquery/jquery-1.9.1.js" ></script>
	<script type="text/javascript" src="js/login.js" charset="GBK"></script>
  <body style="overflow-x:hidden">
<div id="top">
	<div id="logo" onclick="window.location.href='index.jsp'"><img src="imgs/logo.png"  style="width:100%; height:100%;"/></div>
<div id="msg" onclick="alert('联系邮箱为：406727293@qq.com');"><img src="imgs/massage.jpg" style="width:17px; height:18px;" /> "登录页面"改进建议</div>
</div>

<div id="center">
	<div style="width:1210px; height:330px; margin-left:auto; margin-right:auto;">
		<div id="login">
    		<div class="div" style="line-height:40px; font-weight:bold; font-size:20px;margin-bottom:5px;">账户登录</div>
			<div id="info"><span id="error" style="display:<%=display%>">帐号或者密码错误! 请重试!</span></div>
			<div class="div"  style="margin-bottom:30px;"><img src="imgs/loginUser.jpg" style="width:41px; height:42px; float:left;"/><input class="input" id="userName" type="text" onblur="checkUserName();" name="userName" placeholder="手机号/邮箱帐号"/>
			<span id="nullUserName" style="text-align=center; padding-left:5px; color:#F00; font-size:14px; display:none;">账户不能为空</span></div>
      	 	<div  class="div" style="margin-bottom:20px;"><img src="imgs/loginPassWd.jpg" style="width:41px; height:42px; float:left;"/><input class="input" id="passWd"  type="password" name="passWd" onblur="checkPassWd();" placeholder="请输入密码"/>
      	 	<span id="nullPassWd" style="text-align=center; padding-left:5px; color:#F00; font-size:14px; display:none;">密码不能为空</span></div>
        	<div class="div" ><span style="float:left;" class="span" onclick="window.location.href='index.jsp'">忘记登录密码</span><span class="span" style="float:right;"onclick="window.location.href='signUp.jsp'">免费注册</span></div>
        	<div class="div" ><input id="loginbutton" type="button" name="login" value="登&nbsp;&nbsp;&nbsp;录" onclick="checkLogin();"></div>
		</div>
	</div>
</div>



<div id="bottom">
	<div id="bottomLine"></div>
    <div style="line-height:20px;">
	本次项目在windows 10专业版(x64位 版本号:10.0.10586版本10586)系统下完成，前端采用div+css制作.所用工具为《Dreanmweaver cs6》版本，测试所用浏览器：IE11(版本:11.306.10586.0)以及CentBrowser(版本 1.9.10.43 (64-bit)(portable) Chromium内核版本号:50.0.2661.87).后端采用jsp+servlet+javascript+java制作.所用工具:编程环境：MyEclipse 2014GA(版本:12.0.0-20131202 Eclipse-Version: 4.3.1.v20130911-1000 Build id: M20130911-1000); 服务器：Tomcat 9.0.0.M6 ; 数据库：SQL Server 2008 R2. 				本网站仅共本人学习使用
    </div>
</div>
</body>
</html>
