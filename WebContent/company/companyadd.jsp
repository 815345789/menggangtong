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
		var count=1;
		function adddep(){
			$("[name='depList["+(count-1)+"].department_Name']").after('<input type="text" name="depList['+count+'].department_Name" class="form-input-small">');
			count++
		}
		function addcoms(){
			document.addcom.action = "addcom.do";
		 	document.addcom.submit();
		 	window.opener.location.href = "companylist.do";
		 	
			
		}
		
		function closewin(){
			window.close();
		}
	</script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">人员管理</a><a href="">公司管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加公司信息</h3>
			</div>
			<form id="addcom" name="addcom" method="get" action="addcom.do" >
			<div class="public-content-cont two-col">
				<div class="public-cont-left">
					<div class="form-group mt0">
						<label for="">公司全称</label>
						<input type="text" value="" name="company_Name" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">公司简称</label>
						<input type="text" value="" name="company_Abbreviation" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">公司地址</label>
						<input type="text" value="" name="company_Address" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">所属区域</label>
						<select name="using_Site_Id">
							<c:forEach items="${usingsiteList }" var="list">
								<option value="${list.using_Site_Id }">${list.using_Site_Name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="">公司部门</label>
					</div>
					<div class="form-group mt0">
						<input type="text" name="depList[0].department_Name" class="form-input-small"><input type="button" id="add1" value="添加" onclick="adddep()">
					</div>

					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="button" class="sub-btn" value="提    交" onclick="addcoms()" >
						<input type="button" class="sub-btn" value="关   闭" onclick="closewin()">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>