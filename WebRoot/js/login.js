/**
 * loginҳ���javascript
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
		alert("�ʺ�����Ϊ�գ�");
		return false;
	}
	else if(!passWd){	
		alert("��������Ϊ�գ�");
		return false;
	}else{
		$.ajax({
			type:"post", //����ʽ
			url:"login", //���������ַ
			data:"userName="+userName+"&passWd="+passWd,
			success:function(data,textStatus){
				
				if(textStatus=="success"){
					if(data=="false"){
						window.location.href="login.jsp?info=error";
					}else
						window.location.href="index";
				}
				else 
					alert("ajax����servletʧ��");
			}
		});
	} 
}