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
	function updateus(obj){
		var using_Site_Name = $("[name='using_Site_Name']").val()
		var using_Site_Address = $("[name='using_Site_Address']").val()
		$.get(
				"updateus.do",
				{using_Site_Id:obj,using_Site_Name:using_Site_Name,using_Site_Address:using_Site_Address},
				function(date){
					if(date=="true"){
						closewin();
					}
				}
		)
	}
	function closewin(){
		window.opener.location.href = "usingsitelist.do";
		window.close();
	}
	</script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">人员管理</a><a href="">区域管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改区域信息</h3>
			</div>
			<form id="addus" name="addus" method="get" action="addus.do" >
			<div class="public-content-cont two-col">
				<div class="public-cont-left">
					<div class="form-group mt0">
						<label for="">区域名</label>
						<input type="text" value="${usingsite.using_Site_Name }" name="using_Site_Name" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">区域地址</label>
						<input type="text" value="${usingsite.using_Site_Address }" name="using_Site_Address" class="form-input-small">
					</div>
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="button" class="sub-btn" value="提    交" onclick="updateus(${usingsite.using_Site_Id})" >
						<input type="button" class="sub-btn" value="关   闭" onclick="closewin()">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>