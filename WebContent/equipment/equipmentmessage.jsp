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
		var using_Site_Id = $("[name='using_Site_Id']").val()
		$.get(
				"updateequip.do",
				{equipment_Id:obj,using_Site_Id:using_Site_Id},
				function(date){
					if(date=="true"){
						closewin();
					}
				}
		)
	}
	function closewin(){
		window.opener.location.href = "equipmentlist.do";
		window.close();
	}
	function oequip(obj){
		$("[name='using_Site_Id']").val(obj);
	}
	</script>
</head>
<body marginwidth="0" marginheight="0" onload="oequip('${equipment.using_Site_Id }')">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">人员管理</a><a href="">黑名单管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改名单信息</h3>
			</div>
			<form id="updateequip" name="updateequip" method="get" action="updateequip.do" >
			<div class="public-content-cont two-col">
				<div class="public-cont-left">
					<div class="form-group mt0">
						<label for="">设备名</label>
						<input type="text" disabled="disabled" value="${equipment.equipment_Name }" name="equipment_Name" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">设备码</label>
						<input type="text" disabled="disabled" value="${equipment.equipment_Num }" name="equipment_Num" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">分配区域</label>
						<select name="using_Site_Id" value="" >
							<c:forEach items="${usingsiteList }" var="list">
								<option value="${list.using_Site_Id }">${list.using_Site_Name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="button" class="sub-btn" value="提    交" onclick="updateblack(${equipment.equipment_Id})" >
						<input type="button" class="sub-btn" value="关   闭" onclick="closewin()">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>