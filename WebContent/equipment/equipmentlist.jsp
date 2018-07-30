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
		function selectequip(obj){
			$.get(
					"selectequip.do",
					{equipment_Id:obj},
					function(date){
						if(date=="true"){
							var url="equipment/equipmentmessage.jsp";
							openwin(url);
						}
					}
					);
		}
		function deleteequip(obj){
			window.location="deleteequip.do?equipment_Id="+obj
		}
		function openwin(obj){
			window.open(obj,"","top=200,left=300,width=320,height=700");
		}
	</script>
</head>
<body marginwidth="0" marginheight="0" onload="message('${message}')">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a><a href="">职员管理</a></div>
		<div class="public-content">
			<div class="public-content-cont">
				<table class="public-cont-table">
					<tr>
						<th style="width:15%">设备名</th>
						<th style="width:10%">所属区域</th>
						<th style="width:10%">操作</th>
					</tr>
					<c:forEach items="${equipmentpage.list }" var="list">
					<tr>
						<td>${list.equipment_Name }</td>
						<td>
						<c:forEach items="${usingsiteList }" var="usingsite">
							<c:if test="${usingsite.using_Site_Id==list.using_Site_Id }">${usingsite.using_Site_Name }</c:if>
						</c:forEach>
						</td>
						<td>
							<div class="table-fun">
								<input type="button" value="删除" onclick="deleteequip('${list.equipment_Id }')">
								<input type="button" value="修改" onclick="selectequip('${list.equipment_Id }')">
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="page">
					<form action="" method="get">
						<a href="employeeslist.do?pageNo=1">首页</a>
						<c:if test="${equipmentpage.pageNum > 1} ">
						<a href="employeeslist.do?pageNo=${equipmentpage.pageNum - 1 }">上一页</a>						
						</c:if>
						<c:if test="${equipmentpage.pageNum < equipmentpage.pages }">
						<a href="employeeslist.do?pageNo=${equipmentpage.pageNum + 1 }">下一页</a>						
						</c:if>
						第<span style="color:red;font-weight:600">${equipmentpage.pageNum }</span>页
						共<span style="color:red;font-weight:600">${equipmentpage.pages }</span>页
						<input type="text" class="page-input">
						<input type="submit" class="page-btn" value="跳转">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>