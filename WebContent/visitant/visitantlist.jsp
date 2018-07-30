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
	<script type="text/javascript" src="js/WdatePicker.js"></script>
	<script type="text/javascript">
		function message(obj){
			if(obj != "" && obj != undefined){
				alert(obj);
			}
		}
		function selectavm(obj){

			$.get(
					"selectavm.do",
					{visitant_Id:obj},
					function(date){
						if(date=="true"){
							openwin();
						}
					}
					);
		}
		function deletevm(obj){
			window.location="deletevm.do?visitant_Id="+obj
		}
		function openwin(){
			window.open("visitant/visitantmessage.jsp","","top=300,left=300,width=700,height=320");
		}
		function skipvm(obj1,obj2,obj3){
			var pageNo=$("#pageNo").text();
			window.location="visitantList.do?visitant_Name="+obj1+"&visitant_Cardno="+obj2+"&visitant_Entry_Time="+obj3+"&pageNo="+pageNo;
		}
	</script>
</head>
<body marginwidth="0" marginheight="0" onload="message('${message}')">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">管理首页</a><a href="">记录查询</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>修改网站配置</h3>
			</div>
			<div class="public-content-cont">
				<div>
					<form id="selectvm" action="visitantList.do">
						<table class="public-cont-table">
							<td><div class="item-warrp"><span id="item-warrp-all">来访人姓名</span><input type="text" name="visitant_Name"></div></td>
							<td><div class="item-warrp"><span id="item-warrp-in">来访时间</span><input class="Wdate" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" name="visitant_Entry_Time"></div></td>
							<td><div class="item-warrp"><span id="item-warrp-out">身份证号</span><input type="text" name="visitant_Cardno"></div></td>
							<td><input type="reset"></td>
							<td><input type="submit" value="搜索"></td>
						</table>
					</form>
				</div>
				<table class="public-cont-table">
					<tr>
						<th style="width:5%">选择</th>
						<th style="width:13%">姓名</th>
						<th style="width:15%">手机</th>
						<th style="width:21%">来访时间</th>
						<th style="width:13%">被访人姓名</th>
						<th style="width:15%">被访人手机</th>
						<th style="width:8%">是否签离</th>
						<th style="width:10%">操作</th>
					</tr>
					<c:forEach items="${allVisitantPage.list }" var="avm">
					<tr>
						<td><input type="checkbox"></td>
						<td>${avm.visitantMessage.visitant_Name }</td>
						<td>${avm.visitantMessage.visitant_Phone }</td>						
						<td>${avm.visitantMessage.visitant_Entry_Time }</td>
						<td>${avm.employees.employees_Name }</td>
						<td>${avm.employees.employees_Phone }</td>	
						<td>		
						<c:if test="${avm.visitantMessage.visitant_Isleave ==0 }">未离开</c:if>
						<c:if test="${avm.visitantMessage.visitant_Isleave ==1 }">已离开</c:if>	
						</td>
						<td>
							<div class="table-fun">
								<input type="button" value="删除" onclick="deletevm('${avm.visitantMessage.visitant_Id }')">
								<input type="button" value="详情" onclick="selectavm('${avm.visitantMessage.visitant_Id }')">
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="page">
					<form action="" method="get">
						<a href="visitantList.do?visitant_Name=${selectvm.visitant_Name }&visitant_Cardno=${selectvm.visitant_Cardno}&visitant_Entry_Time=${selectvm.visitant_Entry_Time}&pageNo=1">首页</a>
						<c:if test="${allVisitantPage.pageNum > 1} ">
						<a href="visitantList.do?visitant_Name=${selectvm.visitant_Name }&visitant_Cardno=${selectvm.visitant_Cardno}&visitant_Entry_Time=${selectvm.visitant_Entry_Time}&pageNo=${allVisitantPage.pageNum - 1 }">上一页</a>						
						</c:if>
						<c:if test="${allVisitantPage.pageNum < allVisitantPage.pages }">
						<a href="visitantList.do?visitant_Name=${selectvm.visitant_Name }&visitant_Cardno=${selectvm.visitant_Cardno}&visitant_Entry_Time=${selectvm.visitant_Entry_Time}&pageNo=${allVisitantPage.pageNum + 1 }">下一页</a>						
						</c:if>
						第<span style="color:red;font-weight:600">${allVisitantPage.pageNum }</span>页
						共<span style="color:red;font-weight:600">${allVisitantPage.pages }</span>页
						<input type="text" class="page-input" id="pageNo">
						<input type="button" onclick="skipvm('${selectvm.visitant_Name}','${selectvm.visitant_Cardno}','${selectvm.visitant_Entry_Time}')" class="page-btn" value="跳转">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>