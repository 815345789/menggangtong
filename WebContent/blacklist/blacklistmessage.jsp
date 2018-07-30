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
	function updateblack(obj){
		var blacklist_Name = $("[name='blacklist_Name']").val()
		var blacklist_sex = $("[name='blacklist_sex']").val()
		var blacklist_Nation = $("[name='blacklist_Nation']").val()
		var blacklist_Cardno = $("[name='blacklist_Cardno']").val()
		var blacklist_Phone = $("[name='blacklist_Phone']").val()
		var blacklist_Notes = $("[name='blacklist_Notes']").val()
		$.get(
				"updateblack.do",
				{blacklist_Id:obj,blacklist_Name:blacklist_Name,blacklist_sex:blacklist_sex,blacklist_Nation:blacklist_Nation,blacklist_Cardno:blacklist_Cardno,blacklist_Phone:blacklist_Phone,blacklist_Notes:blacklist_Notes},
				function(date){
					if(date=="true"){
						closewin();
					}
				}
		)
	}
	function closewin(){
		window.opener.location.href = "blacklists.do";
		window.close();
	}
	</script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">人员管理</a><a href="">黑名单管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改黑名单信息</h3>
			</div>
			<form id="updateblack" name="updateblack" method="get" action="updateblack.do" >
			<div class="public-content-cont two-col">
				<div class="public-cont-left">
					<div class="form-group mt0">
						<label for="">姓名</label>
						<input type="text" value="${blacklist.blacklist_Name }" name="blacklist_Name" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">性别</label>
						<select name="blacklist_sex" value="${blacklist.blacklist_sex }">
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</div>
					<div class="form-group mt0">
						<label for="">民族</label>
						<input type="text" value="${blacklist.blacklist_Nation }" name="blacklist_Nation" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">身份证号</label>
						<input type="text" value="${blacklist.blacklist_Cardno }" name="blacklist_Cardno" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">手机号</label>
						<input type="text" value="${blacklist.blacklist_Phone }" name="blacklist_Phone" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">备注</label>
						<input type="text" value="${blacklist.blacklist_Notes }" name="blacklist_Notes" class="form-input-small">
					</div>
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="button" class="sub-btn" value="提    交" onclick="updateblack(${blacklist.blacklist_Id})" >
						<input type="button" class="sub-btn" value="关   闭" onclick="closewin()">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>