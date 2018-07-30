<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
	String path = request.getContextPath();     
	String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href=" <%=basePath%>"> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/login.css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>  
	<script type="text/javascript">
	//点击登录按钮
	function login(){
 	    //校验验证码
 	   checkSum();
	}
	function message(obj){
		if(obj !=null && obj != ''){
			$(".warn_text").text(obj);
            $(".login_form_warn").css("display","block");
		}
	}

	//提交表单
	function submitForm(){
 	   document.Login.action = "login.do";
 	   document.Login.submit();
	}


	//获取验证码
	function getVerify(obj){
   		obj.src = "getVerify.do?"+Math.random();
	}
	//校验验证码
	function checkSum(){
	    var inputStr = $(".check_input").val();
	    if(inputStr!=null && inputStr!=""){
	        inputStr = inputStr.toLowerCase();//将输入的字母全部转换成小写
	        $.ajax({
	            url : "checkVerify.do",
	            data: {inputStr:inputStr},
	            success : function(datas) {
	                if(datas == "true"){
	                    submitForm();//提交表单
	                }else{
	                    $(".check_input").val("");
	                    $(".warn_text").text("验证码输入错误！");
	                    $(".login_form_warn").css("display","block");
	                }
	            }
	        });
	    }else{
	        $(".warn_text").text("请输入验证码");
	        $(".login_form_warn").css("display","block");
	    }
	}
	</script>
</head>
<body onload="message('${message}')">
<div class="page">
	<div class="loginwarrp">
		<div class="logo">登陆</div>
        	<div class="login_form">
				<form id="Login" name="Login" method="get" onsubmit="" action="/login">
					<li class="login-item">
						<span>用户名：</span>
						<input type="text" name="username" class="login_input">
					</li>
					<li class="login-item">
						<span>密　码：</span>
						<input type="password" name="password" class="login_input">
					</li>               
    	       </form>
    	     		<li class="login-item">
						<span>密　码：</span>
						<input class="check_input" type="text"  placeholder="请输入验证码">
						<img id="imgVerify" src="getVerify.do" alt="点击更换验证码" width="112" height="36" onclick="getVerify(this);">
					</li> 
               <div class="login_form_warn">
                    <span class="warn_text"></span>
               </div>
           	   <div class="login-sub">
                    <button class="btn_submit" onclick="login();">登&nbsp;&nbsp;&nbsp;录</button>
               </div>
		   </div>
	</div>
</div>
</body>
</html>