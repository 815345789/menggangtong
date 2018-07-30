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
	var count=2;
		function deletedep(obj1,obj2){
			$.get(
					"deletedep.do",
					{department_Id:obj1},
					function(date){
						if(date=="true"){
							$("[name='"+obj2+"']").remove();
						}
					}
					);
		}
		function adddep(obj1,obj2){
			var name=$("#"+obj2).val();
			$.get(
					"adddep.do",
					{company_Id:obj1,department_Name:name},
					function(date){
						if(date=="true"){
							location.reload();
							//$("#"+obj2).addend('<input type="text" id="new'+count+'" class="form-input-small"><input type="button" value="添加" onclick="adddep("${company.company_Id}","new'+count+'")">')
						}
					}
			)
		}
		function updatecom(obj){
			var company_Name = $("[name='company_Name']").val()
			var company_Abbreviation = $("[name='company_Abbreviation']").val()
			var company_Address = $("[name='company_Address']").val()
			$.get(
					"updatecom.do",
					{company_Id:obj,company_Name:company_Name,company_Abbreviation:company_Abbreviation,company_Address:company_Address},
					function(date){
						if(date=="true"){
							closewin();
						}
					}
			)
		}
		function closewin(){
			window.opener.location.href = "companylist.do";
			window.close();
		}
	</script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">人员管理</a><a href="">公司管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改公司信息</h3>
			</div>
			<form action="">
			<div class="public-content-cont two-col">
				<div class="public-cont-left">
					<div class="form-group mt0">
						<label for="">公司全称</label>
						<input type="text" value="${company.company_Name }" name=“company_Name” class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">公司简称</label>
						<input type="text" value="${company.company_Abbreviation }" name="company_Abbreviation" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">公司地址</label>
						<input type="text" value="${company.company_Address }" name="company_Address" class="form-input-small">
					</div>
					<div class="form-group">
						<label for="">公司部门</label>
					</div>
					<div class="form-group mt0">
						<c:forEach items="${company.depList }" var="deplist" varStatus="status">
							<input type="text" value="${deplist.department_Name } " name="${status.count }"  class="form-input-small"><input type="button" name="${status.count }" value="删除" onclick="deletedep('${deplist.department_Id }','${status.count }')">
						</c:forEach>
						<input type="text" id="new1" class="form-input-small"><input type="button" id="add1" value="添加" onclick="adddep('${company.company_Id}','new1')">
					</div>
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="button" class="sub-btn" value="修   改" onclick="updatecom('${company.company_Id}')">
						<input type="button" class="sub-btn" value="关   闭" onclick="closewin()">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>