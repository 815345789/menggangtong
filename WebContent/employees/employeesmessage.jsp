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
		var employees_Name = $("[name='employees_Name']").val()
		var employees_Sex = $("[name='employees_Sex']").val()
		var employees_Job = $("[name='employees_Job']").val()
		var company_Id = $("[name='company.company_Id']").val()
		var department_Id = $("[name='department.department_Id']").val()
		var employees_Phone = $("[name='employees_Phone']").val()
		$.get(
				"updateemp.do",
				{employees_Id:obj,employees_Name:employees_Name,employees_Sex:employees_Sex,employees_Job:employees_Job,'company.company_Id':company_Id,'department.department_Id':department_Id,employees_Phone:employees_Phone},
				function(date){
					if(date=="true"){
						closewin();
					}
				}
		)
	}
	function closewin(){
		window.opener.location.href = "employeeslist.do";
		window.close();
	}
	function odep(obj1,obj2){
		$("#department_Name").empty(); 
		$("#company_Name").val(obj1);
		$.get(
				"selectecd.do",
				{company_Id:obj1},
				function(data){
					for(var i=0;i<data.length;i++){
						$("#department_Name").append("<option value='"+data[i].department_Id+"'>"+data[i].department_Name+"</option>");
					}
					$("#department_Name").val(obj2);
				},"json"
				);
	}
	function changecom(obj){
		$("#department_Name").empty(); 
		$.get(
				"selectecd.do",
				{company_Id:obj},
				function(data){
					for(var i=0;i<data.length;i++){
						$("#department_Name").append("<option value='"+data[i].department_Id+"'>"+data[i].department_Name+"</option>");
					}
				},"json"
				);
	}
	</script>
</head>
<body marginwidth="0" marginheight="0" onload="odep('${employees.company.company_Id }','${employees.department.department_Id }')">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">人员管理</a><a href="">账户管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改职员信息</h3>
			</div>
			<form id="updateemp" name="updateemp" method="get" action="updateemp.do" >
			<div class="public-content-cont two-col">
				<div class="public-cont-left">
					<div class="form-group mt0">
						<label for="">姓名</label>
						<input type="text" value="${employees.employees_Name }" name="employees_Name" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">性别</label>
							<select name="employees_Sex" value="${employees.employees_Sex }">
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</div>
					<div class="form-group mt0">
						<label for="">工作</label>
						<input type="text" value="${employees.employees_Job }" name="employees_Job" class="form-input-small">
						
					</div>
					<div class="form-group mt0">
						<label for="">单位名</label>
						<select name="company.company_Id" value="" id="company_Name" onchange="changecom(this.options[this.selectedIndex].value)">
							<c:forEach items="${comlist }" var="list">
								<option value="${list.company_Id }">${list.company_Name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group mt0">
						<label for="">部门名</label>
						<select name="department.department_Id" value="" id="department_Name">
						</select>
					</div>
					<div class="form-group mt0">
						<label for="">电话</label>
						<input type="text" value="${employees.employees_Phone }" name="employees_Phone" class="form-input-small">
					</div>
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="button" class="sub-btn" value="提    交" onclick="updateum('${employees.employees_Id}')" >
						<input type="button" class="sub-btn" value="关   闭" onclick="closewin()">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>