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
		function selectum(obj){
			$.get(
					"selectum.do",
					{user_Id:obj},
					function(date){
						if(date=="true"){
							var url="usermessage/usermessage.jsp";
							openwin(url);
						}
					}
					);
		}
		function deleteum(obj){
			window.location="deleteum.do?user_Id="+obj
		}
		function openwin(obj){
			window.open(obj,"","top=200,left=300,width=320,height=700");
		}
	</script>
</head>
<body marginwidth="0" marginheight="0" onload="message('${message}')">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a><a href="">账户管理</a></div>
		<div class="public-content">
			<div class="public-content-cont">
				<input type="button" value="新增账户" onclick="openwin('usermessage/usermessageadd.jsp')">
				<table class="public-cont-table">
					<tr>
						<th style="width:15%">用户名</th>
						<th style="width:10%">密码</th>
						<th style="width:10%">操作</th>
					</tr>
					<c:forEach items="${usermessagepage.list }" var="list">
					<tr>
						<td>${list.username }</td>
						<td>${list.password }</td>
						<td>
							<div class="table-fun">
								<input type="button" value="删除" onclick="deleteum('${list.user_Id }')">
								<input type="button" value="修改" onclick="selectum('${list.user_Id }')">
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="page">
					<form action="" method="get">
						<a href="usermessagelist.do?pageNo=1">首页</a>
						<c:if test="${usermessagepage.pageNum > 1} ">
						<a href="usermessagelist.do?pageNo=${usermessagepage.pageNum - 1 }">上一页</a>						
						</c:if>
						<c:if test="${usermessagepage.pageNum < usermessagepage.pages }">
						<a href="usermessagelist.do?pageNo=${usermessagepage.pageNum + 1 }">下一页</a>						
						</c:if>
						第<span style="color:red;font-weight:600">${usermessagepage.pageNum }</span>页
						共<span style="color:red;font-weight:600">${usermessagepage.pages }</span>页
						<input type="text" class="page-input">
						<input type="submit" class="page-btn" value="跳转">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>