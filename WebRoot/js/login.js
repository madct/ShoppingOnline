/**
 * login页面的javascript
 */

function checkUserName(){
	var userName=document.getElementById("userName").value;
	var spanNU=document.getElementById("nullUserName");
	
	if(!userName)
		spanNU.style.display="block";
	else
		spanNU.style.display="none";	
}

function checkPassWd(){
	var passWd=document.getElementById("passWd").value;
	var spanNP=document.getElementById("nullPassWd");
	
	if(!passWd)
		spanNP.style.display="block";
	else
		spanNP.style.display="none";	
}

function checkLogin(){
	var userName=document.getElementById("userName").value;
	var passWd=document.getElementById("passWd").value;
	if(!userName){
		alert("帐号输入为空！");
		return false;
	}
	else if(!passWd){	
		alert("密码输入为空！");
		return false;
	}else{
		$.ajax({
			type:"post", //请求方式
			url:"login", //发送请求地址
			data:"userName="+userName+"&passWd="+passWd,
			success:function(data,textStatus){
				
				if(textStatus=="success"){
					if(data=="false"){
						window.location.href="login.jsp?info=error";
					}else
						window.location.href="index";
				}
				else 
					alert("ajax链接servlet失败");
			}
		});
	} 
}