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
		function deletecom(obj){
			window.location="deletecom.do?company_Id="+obj
		}
		function selectcom(obj){
			$.get(
					"selectcom.do",
					{company_Id:obj},
					function(date){
						if(date=="true"){
							var url="company/companymessage.jsp";
							openwin(url);
						}
					}
					);
		}
		function openwin(obj){
			window.open(obj,"","top=200,left=300,width=320,height=700");
		}
		function message(obj){
			if(obj != "" && obj != undefined){
				alert(obj);
			}
		}
	</script>
</head>
<body marginwidth="0" marginheight="0" onload="message('${message}')">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a><a href="">公司查询</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改网站配置</h3>
			</div>
			<div class="public-content-cont">
				<div>
					<form id="selectcm" action="companylist.do">
						<table class="public-cont-table">
							<td><div class="item-warrp"><span id="item-warrp-all">公司名</span><input type="text" name="company_Name"></div></td>
							<td><div class="item-warrp"><span id="item-warrp-in">公司地址</span><input type="text" name="company_Address"></div></td>
							<td><input type="reset"></td>
							<td><input type="submit" value="搜索"></td>
						</table>
					</form>
				</div>
				<input type="button" value="新增公司" onclick="openwin('company/companyadd.jsp')">
				<table class="public-cont-table">
					<tr>
						<th style="width:13%">公司名</th>
						<th style="width:15%">公司简称</th>
						<th style="width:21%">公司地址</th>
						<th style="width:10%">操作</th>
					</tr>
					<c:forEach items="${companypage.list }" var="com">
					<tr>
						<td>${com.company_Name }</td>
						<td>${com.company_Abbreviation }</td>						
						<td>${com.company_Address }</td>
						<td>
							<div class="table-fun">
								<input type="button" value="删除" onclick="deletecom('${com.company_Id }')">
								<input type="button" value="修改" onclick="selectcom('${com.company_Id  }')">
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="page">
					<form action="" method="get">
						<a href="companylist.do?company_Name=${selectcom.company_Name }&company_Address=${selectcom.company_Address}&pageNo=1">首页</a>
						<c:if test="${companypage.pageNum > 1} ">
						<a href="companylist.do?company_Name=${selectcom.company_Name }&company_Address=${selectcom.company_Address}&pageNo=${compantpage.pageNum - 1 }">上一页</a>						
						</c:if>
						<c:if test="${companypage.pageNum < companypage.pages }">
						<a href="companylist.do?company_Name=${selectcom.company_Name }&company_Address=${selectcom.company_Address}&pageNo=${compantpage.pageNum + 1 }">下一页</a>						
						</c:if>
						第<span style="color:red;font-weight:600">${companypage.pageNum }</span>页
						共<span style="color:red;font-weight:600">${companypage.pages }</span>页
						<input type="text" class="page-input">
						<input type="submit" class="page-btn" value="跳转">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>