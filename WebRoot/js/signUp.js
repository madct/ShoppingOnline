/**
 *  ����signUpҳ���javaScript
 */



	
/*������ʾ��ʽ*/
function setTab(name,cursel,n){
for(i=1;i<=2;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById(n+i);
menu.style.backgroundColor=i==cursel?"#FF5A59":"#999";
con.style.display=i==cursel?"block":"none";
}
}

/*����ֻ�ע���ʺ�*/
function checkMobile(){
	var mobile=document.getElementById("mobile").value;
	var spanNM=document.getElementById("nullMobile");
	var spanEM=document.getElementById("errorMobile");
	var spanHM=document.getElementById("hadMobile");
	
	if(!mobile){
		spanNM.style.display="block";
		spanEM.style.display="none";	
		spanHM.style.display="none";
	}else if(!(/^[1]+[3,4,5,8]+\d{9}$/.test(mobile))){
		spanNM.style.display="none";	
		spanHM.style.display="none";
		spanEM.style.display="block";
	}else if((/^[1]+[3,4,5,8]+\d{9}$/.test(mobile))){
			$.ajax({
			type:"post", //����ʽ
			url:"signCheck", //���������ַ
			data:"userName="+mobile,
			success:function(data,textStatus){
				if(textStatus=="success"){
					if(data=="true"){
						spanNM.style.display="none";	
						spanEM.style.display="none";
						spanHM.style.display="block";
					}else {
						spanHM.style.display="none";
						spanNM.style.display="none";
						spanEM.style.display="none";
					}		
				}else
					alert("ajax����servletʧ��");	
			}
	});}else {
			spanHM.style.display="none";
			spanNM.style.display="none";
			spanEM.style.display="none";
		}
}

/*��������Ƿ���ȷ*/
function checkPassWd(){
	var passWd=document.getElementById("passWd").value;
	var spanNP=document.getElementById("nullPassWd");
	var spanEP=document.getElementById("errorPassWd");
	
	if(!passWd){
		spanNP.style.display="block";
		spanEP.style.display="none";	
	}else if(!(/^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,16}$/.test(passWd))){
		spanNP.style.display="none";
		spanEP.style.display="block";
	}else{
		spanNP.style.display="none";
		spanEP.style.display="none";
	}

}

/*����ظ������Ƿ�һ��*/
function checkRepeat(){
	var RepeatPassWd=document.getElementById("repeatPassWd").value;
	var passWd=document.getElementById("passWd").value;
	var spanECR=document.getElementById("errorRepeat");
	var spanNCR=document.getElementById("nullRepeat");
	if(!RepeatPassWd){
		spanNCR.style.display="block";
		spanECR.style.display="none";
	}else if(RepeatPassWd!=passWd){
		spanECR.style.display="block";
		spanNCR.style.display="none";
	}else{
		spanECR.style.display="none";
		spanNCR.style.display="none";
	}
}


/*����ǳ��Ƿ��Ѵ���*/
function checkNickName(){
	var nickName=document.getElementById("nickName").value;
	var spanNN=document.getElementById("nullNickName");
	var spanHN=document.getElementById("hadNickName");
	
	if(!nickName){
		spanNN.style.display="block";
		spanHN.style.display="none";
	}else if(nickName){
		$.ajax({
			type:"post", //����ʽ
			url:"signCheck", //���������ַ
			data:"nickName="+nickName,
			success:function(data,textStatus){
				if(textStatus=="success"){
					if(data=="true"){
						spanNN.style.display="none";
						spanHN.style.display="block";
					}else {
						spanNN.style.display="none";
						spanHN.style.display="none";
					}		
				}else
					alert("ajax����servletʧ��");
			}
		});
	}else {
		spanNN.style.display="none";
		spanHN.style.display="none";
	}
	
}


/*��������ʺ�*/
function checkEmail(){
	var email=document.getElementById("email").value;
	var spanNE=document.getElementById("nullEmail");
	var spanEE=document.getElementById("errorEmail");
	var spanHE=document.getElementById("hadEmail");
	
	if(!email){
		spanNE.style.display="block";
		spanEE.style.display="none";
		spanHE.style.display="none";
	}else if(!(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email))){
		spanNE.style.display="none";
		spanEE.style.display="block";
		spanHE.style.display="none";
	}else if((/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email))){
		$.ajax({
			type:"post",
			url:"signCheck",
			data:"userName="+email,
			success:function(data,textStatus){
				if(textStatus=="success"){
					if(data=="true"){
						spanNE.style.display="none";
						spanEE.style.display="none";
						spanHE.style.display="block";
					}else{
						spanNE.style.display="none";
						spanEE.style.display="none";
						spanHE.style.display="none";
					}
				}else
					alert("ajax����servletʧ��");
			}
		});
	}else{
		spanNE.style.display="none";
		spanEE.style.display="none";
		spanHE.style.display="none";
	}
}

/*���E�����Ƿ���ȷ*/
function checkPassWdE(){
	var passWd=document.getElementById("passWdE").value;
	var spanNP=document.getElementById("nullPassWdE");
	var spanEP=document.getElementById("errorPassWdE");
	
	if(!passWd){
		spanNP.style.display="block";
		spanEP.style.display="none";	
	}else if(!(/^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,16}$/.test(passWd))){
		spanNP.style.display="none";
		spanEP.style.display="block";
	}else{
		spanNP.style.display="none";
		spanEP.style.display="none";
	}

}


/*���E�ظ������Ƿ�һ��*/
function checkRepeatE(){
	var RepeatPassWd=document.getElementById("repeatPassWdE").value;
	var passWd=document.getElementById("passWdE").value;
	var spanECR=document.getElementById("errorRepeatE");
	var spanNCR=document.getElementById("nullRepeatE");
	if(!RepeatPassWd){
		spanNCR.style.display="block";
		spanECR.style.display="none";
	}else if(RepeatPassWd!=passWd){
		spanECR.style.display="block";
		spanNCR.style.display="none";
	}else{
		spanECR.style.display="none";
		spanNCR.style.display="none";
	}
}


/*����ǳ��Ƿ��Ѵ���*/
function checkNickNameE(){
	var nickName=document.getElementById("nickNameE").value;
	var spanNN=document.getElementById("nullNickNameE");
	var spanHN=document.getElementById("hadNickNameE");
	
	if(!nickName){
		spanNN.style.display="block";
		spanHN.style.display="none";
	}else if(nickName){
		$.ajax({
			type:"post", //����ʽ
			url:"signCheck", //���������ַ
			data:"nickName="+nickName,
			success:function(data,textStatus){
				if(textStatus=="success"){
					if(data=="true"){
						spanNN.style.display="none";
						spanHN.style.display="block";
					}else {
						spanNN.style.display="none";
						spanHN.style.display="none";
					}		
				}else
					alert("ajax����servletʧ��");
			}
		});
	}else {
		spanNN.style.display="none";
		spanHN.style.display="none";
	}
	
}



/*mobileSubmitCheckSignUp*/
function submitCheckSignUpMoblie(){
	var mobile=document.getElementById("mobile").value;
	var spanEM=document.getElementById("errorMobile");
	var spanHM=document.getElementById("hadMobile");
	
	var passWd=document.getElementById("passWd").value;
	var spanEP=document.getElementById("errorPassWd");
	
	var RepeatPassWd=document.getElementById("repeatPassWd").value;
	var spanECR=document.getElementById("errorRepeat");
	
	var nickName=document.getElementById("nickName").value;
	var spanHN=document.getElementById("hadNickName");
	
	if(!mobile){
		alert("�ֻ��Ų���Ϊ��!");
		return false;
	}
	else if(!passWd){
		alert("���벻��Ϊ��!");
		return false;
		}
	else if(!RepeatPassWd){
		alert("�ظ����벻��Ϊ��!");
		return false;
		}
	else if(!nickName){
		alert("�ǳƲ���Ϊ��!");
		return false;
		}
	else if(spanEM.style.display=="block"){
		alert("�ֻ��Ÿ�ʽ����!");
		return false;
	}else if(spanHM.style.display=="block"){
		alert("���ֻ�����ע��!");
		return false;
	}else if(spanEP.style.display=="block"){
		alert("�����ʽ����!");
		return false;
	}else if(spanECR.style.display=="block"){
		alert("�ظ��������!");
		return false;
	}else if(spanHN.style.display=="block"){
		alert("���ǳ��Ѵ���!");
		return false;
	}else
		return true;
}


/*emailSubmitCheckSignUp*/
function submitCheckSignUpEmail(){
	var email=document.getElementById("email").value;
	var spanEE=document.getElementById("errorEmail");
	var spanHE=document.getElementById("hadEmail");
	
	var passWd=document.getElementById("passWdE").value;
	var spanEP=document.getElementById("errorPassWdE");
	
	var RepeatPassWd=document.getElementById("repeatPassWdE").value;
	var spanECR=document.getElementById("errorRepeatE");
	
	var nickName=document.getElementById("nickNameE").value;
	var spanHN=document.getElementById("hadNickNameE");
	
	if(!email){
		alert("�����ʺŲ���Ϊ��!");
		return false;
	}
	else if(!passWd){
		alert("���벻��Ϊ��!");
		return false;
		}
	else if(!RepeatPassWd){
		alert("�ظ����벻��Ϊ��!");
		return false;
		}
	else if(!nickName){
		alert("�ǳƲ���Ϊ��!");
		return false;
		}
	else if(spanEE.style.display=="block"){
		alert("�����ʺŸ�ʽ����!");
		return false;
	}else if(spanHE.style.display=="block"){
		alert("�������ʺ���ע��!");
		return false;
	}else if(spanEP.style.display=="block"){
		alert("�����ʽ����!");
		return false;
	}else if(spanECR.style.display=="block"){
		alert("�ظ��������!");
		return false;
	}else if(spanHN.style.display=="block"){
		alert("���ǳ��Ѵ���!");
		return false;
	}else
		return true;
}

