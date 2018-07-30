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
<head>
<base href=" <%=basePath%>"> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
		function selectemp(obj){
			$.get(
					"selectemp.do",
					{employees_Id:obj},
					function(date){
						if(date=="true"){
							var url="employees/employeesmessage.jsp";
							openwin(url);
						}
					}
					);
		}
		function deleteemp(obj){
			window.location="deleteemp.do?employees_Id="+obj
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
				<input type="button" value="新增职员" onclick="openwin('employees/employeesadd.jsp')">
				<table class="public-cont-table">
					<tr>
						<th style="width:15%">姓名</th>
						<th style="width:10%">性别</th>
						<th style="width:10%">工作</th>
						<th style="width:15%">单位名</th>
						<th style="width:10%">单位地址</th>
						<th style="width:10%">部门名</th>
						<th style="width:10%">电话</th>
						<th style="width:10%">所属地区</th>
						<th style="width:10%">操作</th>
					</tr>
					<c:forEach items="${emppage.list }" var="list">
					<tr>
						<td>${list.employees_Name }</td>
						<td>${list.employees_Sex== 0?'男':'女' }</td>
						<td>${list.employees_Job }</td>
						<td>${list.company.company_Name }</td>
						<td>${list.company.company_Address }</td>
						<td>${list.department.department_Name }</td>
						<td>${list.employees_Phone }</td>
						<td>
						<c:forEach items="${usingsiteList }" var="usingsite">
							<c:if test="${usingsite.using_Site_Id==list.company.using_Site_Id }">${usingsite.using_Site_Name }</c:if>
						</c:forEach>
						</td>
						<td>
							<div class="table-fun">
								<input type="button" value="删除" onclick="deleteemp('${list.employees_Id }')">
								<input type="button" value="修改" onclick="selectemp('${list.employees_Id }')">
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="page">
					<form action="" method="get">
						<a href="employeeslist.do?pageNo=1">首页</a>
						<c:if test="${emppage.pageNum > 1} ">
						<a href="employeeslist.do?pageNo=${emppage.pageNum - 1 }">上一页</a>						
						</c:if>
						<c:if test="${emppage.pageNum < emppage.pages }">
						<a href="employeeslist.do?pageNo=${emppage.pageNum + 1 }">下一页</a>						
						</c:if>
						第<span style="color:red;font-weight:600">${emppage.pageNum }</span>页
						共<span style="color:red;font-weight:600">${emppage.pages }</span>页
						<input type="text" class="page-input">
						<input type="submit" class="page-btn" value="跳转">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>