<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link href="myCss/signUp.css" rel="stylesheet"  type="text/css" />
	<title>注册页面-zero网购商城</title>
	<link rel="shortcut icon" href="favicon.ico" />
</head>
<script type="text/JavaScript" src="jquery/jquery-1.9.1.js" ></script>
<script type="text/JavaScript" src="js/signUp.js" charset="GBK";></script>

<body  style="overflow-x:hidden" >
<div id="all">
	<div id="type">
   		<div id="signUpLogo"><img src="imgs/logo.png" onclick="window.location.href='index'"/></div>
		<div id="signType">	
			<ul style="margin-left:auto; margin-right:auto; width:450px;">
        		<li id="Type1" onclick="setTab('Type',1,'show')" class="signType" style=" background-color:#FF5A59; font-size:20px;" >手机注册(推荐)</li>
          		<li id="Type2" onclick="setTab('Type',2,'show')" class="signType" style=" background-color:#999;font-size:20px;" >邮箱注册</li>
        	</ul>
   		</div>
        <div id="show1">
        	<form action="signUp" method="post" onsubmit="return submitCheckSignUpMoblie();">
        	<div id="butSex"><input class="input" type="text" id="mobile" name="userName" placeholder="手机号" onblur="checkMobile()"/><span id="nullMobile">手机号不能为空</span><span id="errorMobile">手机号格式错误</span><span id="hadMobile">该手机号已注册过</span></div>
            <div id="butSex"><input class="input" type="password" id="passWd" name="passWd" placeholder="密码(由6-16位英文字母及数字组成)" onblur="checkPassWd()"/><span id="nullPassWd">密码不能为空</span><span id="errorPassWd">密码不符合规则</span></div>
            <div id="butSex"><input class="input" type="password" id="repeatPassWd" name="repeatPassWd" placeholder="重复密码" onblur="checkRepeat()"/><span id="errorRepeat">两次输入的密码不一致</span><span id="nullRepeat">重复密码为空</span></div>
            <div id="butSex"><input class="input" type="text" id="nickName" name="nickName" placeholder="帐号昵称" onblur="checkNickName()"/><span id="nullNickName">昵称不能为空</span><span id="hadNickName">昵称已存在</span></div>
            <div id="sex">性别:<input name="sex"  type="radio" value="男" checked style="width:17px; height:17px; margin-left:20px" /> 男<input name="sex" type="radio" value="女" style="width:17px; height:17px; margin-left:40px"/> 女</div>
            <div id="butSex" style="margin-top:35px;"><input type="submit" name="signUp" value="免费注册帐号" class="sign" /></div>
            <div id="butSex" style="margin-top:15px;"><input type="button" name="login" value="已经有账号了？"class="login" onclick="window.location.href='login.jsp'"/></div>
            </form>
        </div>
        <div id="show2" style="display:none">
       		<form action="signUp" method="post" onsubmit="return submitCheckSignUpEmail();">
        	<div id="butSex"><input class="input" type="text" id="email" name="userName" placeholder="邮箱帐号" onblur="checkEmail()"/><span id="nullEmail">邮箱帐号不能为空</span><span id="errorEmail">邮箱帐号格式错误</span><span id="hadEmail">该邮箱帐号已注册过</span></div>
            <div id="butSex"><input class="input" type="password" id="passWdE" name="passWd" placeholder="密码(由6-16位英文字母及数字组成)" onblur="checkPassWdE()"/><span id="nullPassWdE">密码不能为空</span><span id="errorPassWdE">密码不符合规则</span></div>
            <div id="butSex"><input class="input" type="password" id="repeatPassWdE" name="repeatPassWd" placeholder="重复密码" onblur="checkRepeatE()"/><span id="errorRepeatE">两次输入的密码不一致</span><span id="nullRepeatE">重复密码为空</span></div>
            <div id="butSex"><input class="input" type="text" id="nickNameE" name="nickName" placeholder="帐号昵称" onblur="checkNickNameE()"/><span id="nullNickNameE">昵称不能为空</span><span id="hadNickNameE">昵称已存在</span></div>
            <div id="sex">性别:<input name="sex"  type="radio" value="男" checked style="width:17px; height:17px; margin-left:20px" /> 男<input name="sex" type="radio" value="女" style="width:17px; height:17px; margin-left:40px"/> 女</div>
            <div id="butSex" style="margin-top:35px;"><input type="submit" name="signUp" value="免费注册帐号" class="sign" /></div>
            <div id="butSex" style="margin-top:15px;"><input type="button" name="login" value="已经有账号了？"class="login" onclick="window.location.href='login.jsp'"/></div>
            </form>
        </div>
	</div>
</div>

</body>
</html>
