<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
	<script type="text/javascript" src="js/jquery.min.js"></script> 
	<script type="text/javascript">
	function updateum(obj){
		var username = $("[name='username']").val()
		var password = $("[name='password']").val()
		var usermessage_name = $("[name='usermessage_name']").val()
		$.get(
				"updateum.do",
				{user_Id:obj,username:username,password:password,usermessage_name:usermessage_name},
				function(date){
					if(date=="true"){
						closewin();
					}
				}
		)
	}
	function closewin(){
		window.opener.location.href = "usermessagelist.do";
		window.close();
	}
	</script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">人员管理</a><a href="">账户管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改账户信息</h3>
			</div>
			<form id="addus" name="addus" method="get" action="addus.do" >
			<div class="public-content-cont two-col">
				<div class="public-cont-left">
					<div class="form-group mt0">
						<label for="">用户名</label>
						<input type="text" value="${user.username }" name="username" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">密码</label>
						<input type="text" value="${user.password }" name="password" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">账户信息</label>
						<input type="text" value="${user.usermessage_name }" name="usermessage_name" class="form-input-small">
					</div>
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="button" class="sub-btn" value="提    交" onclick="updateum(${user.user_Id})" >
						<input type="button" class="sub-btn" value="关   闭" onclick="closewin()">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>