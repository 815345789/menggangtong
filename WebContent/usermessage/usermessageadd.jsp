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
		function addums(){
			document.addum.action = "addum.do";
		 	document.addum.submit();
		 	window.opener.location.href = "usermessagelist.do";
		}
		
		function closewin(){
			window.close();
		}
	</script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">人员管理</a><a href="">账户管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加帐户信息</h3>
			</div>
			<form id="addum" name="addum" method="get" action="addum.do" >
			<div class="public-content-cont two-col">
				<div class="public-cont-left">
					<div class="form-group mt0">
						<label for="">用户名</label>
						<input type="text" value="" name="username" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">密码</label>
						<input type="text" value="" name="password" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">账户信息</label>
						<input type="text" value="" name="usermessage_name" class="form-input-small">
					</div>
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="button" class="sub-btn" value="提    交" onclick="addums()" >
						<input type="button" class="sub-btn" value="关   闭" onclick="closewin()">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>