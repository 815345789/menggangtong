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
		function message(obj){
			if(obj != "" && obj != undefined){
				alert(obj);
			}
		}
		function selectblack(obj){
			$.get(
					"selectblack.do",
					{blacklist_Id:obj},
					function(date){
						if(date=="true"){
							var url="blacklist/blacklistmessage.jsp";
							openwin(url);
						}
					}
					);
		}
		function deleteblack(obj){
			document.write("<form action='deleteblack.do' method='post' name='form1' style='display:none'>");  
			document.write("<input type='text' name='blacklist_Id' value="+obj+">");  
			document.write("</form>");  
			document.form1.submit(); 
			//window.location="deleteblack.do?blacklist_Id="+obj
		}
		function openwin(obj){
			window.open(obj,"","top=200,left=300,width=320,height=700");
		}
	</script>
</head>
<body marginwidth="0" marginheight="0" onload="message('${message}')">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a><a href="">黑名单管理</a></div>
		<div class="public-content">
			<div class="public-content-cont">
				<div>
					<form id="selectblack" action="blacklists.do">
						<table class="public-cont-table">
							<td><div class="item-warrp"><span id="item-warrp-all">姓名</span><input type="text" name="blacklist_Name"></div></td>
							<td><div class="item-warrp"><span id="item-warrp-in">电话号码</span><input type="text" name="blacklist_Phone"></div></td>
							<td><div class="item-warrp"><span id="item-warrp-out">身份证号</span><input type="text" name="blacklist_Cardno"></div></td>
							<td><input type="reset"></td>
							<td><input type="submit" value="搜索"></td>
						</table>
					</form>
				</div>
				<input type="button" value="新增黑名单" onclick="openwin('blacklist/blacklistadd.jsp')">
				<input type="button" value="批量导入黑名单" onclick="openwin('blacklist/allblacklistadd.jsp')">
				<table class="public-cont-table">
					<tr>
						<th style="width:15%">姓名</th>
						<th style="width:10%">性别</th>
						<th style="width:10%">民族</th>
						<th style="width:21%">身份证号</th>
						<th style="width:21%">手机号码</th>
						<th style="width:10%">操作</th>
					</tr>
					<c:forEach items="${blistspage.list }" var="list">
					<tr>
						<td>${list.blacklist_Name }</td>
						<td>${list.blacklist_sex == 0?'男':'女' }</td>
						<td>${list.blacklist_Nation }</td>
						<td>${list.blacklist_Cardno }</td>
						<td>${list.blacklist_Phone }</td>
						<td>
							<div class="table-fun">
								<input type="button" value="删除" onclick="deleteblack('${list.blacklist_Id }')">
								<input type="button" value="修改" onclick="selectblack('${list.blacklist_Id }')">
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="page">
					<form action="" method="get">
						<a href="blacklists.do?blacklist_Name=${selectblack.blacklist_Name }&blacklist_Phone=${selectblack.blacklist_Phone}&blacklist_Cardno=${selectblack.blacklist_Cardno}&pageNo=1">首页</a>
						<c:if test="${blistspage.pageNum > 1} ">
						<a href="blacklists.do?blacklist_Name=${selectblack.blacklist_Name }&blacklist_Phone=${selectblack.blacklist_Phone}&blacklist_Cardno=${selectblack.blacklist_Cardno}&pageNo=${blistspage.pageNum - 1 }">上一页</a>						
						</c:if>
						<c:if test="${blistspage.pageNum < blistspage.pages }">
						<a href="blacklists.do?blacklist_Name=${selectblack.blacklist_Name }&blacklist_Phone=${selectblack.blacklist_Phone}&blacklist_Cardno=${selectblack.blacklist_Cardno}&pageNo=${blistspage.pageNum + 1 }">下一页</a>						
						</c:if>
						第<span style="color:red;font-weight:600">${blistspage.pageNum }</span>页
						共<span style="color:red;font-weight:600">${blistspage.pages }</span>页
						<input type="text" class="page-input">
						<input type="submit" class="page-btn" value="跳转">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>