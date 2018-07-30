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
	<link rel="stylesheet" href="../css/reset.css" />
	<link rel="stylesheet" href="../css/content.css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>  
	<script type="text/javascript">
		
	</script>
</head>
<body>
<div class="public-content-cont">
	<table class="public-cont-table">
	 <tr>
	 	<td style="width:10%">姓&nbsp;&nbsp;名</td>
	 	<td style="width:10%"><input type="text" size="7" value="${allvisitantmessage.visitantMessage.visitant_Name }" disabled></td>
	 	<td style="width:10%">民&nbsp;&nbsp;族</td>
	 	<td style="width:10%"><input type="text" size="7" value="${allvisitantmessage.visitantMessage.visitant_Nation }" disabled></td>
	 	<td style="width:10%">性&nbsp;&nbsp;别</td>
	 	
	 	<td style="width:10%"><input type="text" size="7" value="${allvisitantmessage.visitantMessage.visitant_Sex == 0?'男':'女' }" disabled></td>
	 	<td style="width:40%" rowspan="5"></td>

	 </tr>
	  <tr>
	  	<td style="width:10%">身份证号</td>
	 	<td colspan="2" style="width:20%"><input type="text" value="${allvisitantmessage.visitantMessage.visitant_Cardno }" disabled></td>
	 	<td style="width:10%">家庭住址</td>
	 	<td colspan="3" style="width:20%"><input type="text" value="${allvisitantmessage.visitantMessage.visitant_Address }" disabled></td>
	 </tr>
	  <tr>
	 	<td style="width:10%">来访单位</td>
	 	<td colspan="2"><input type="text" value="${allvisitantmessage.visitantMessage.visitant_Companyname }" disabled></td>
	 	<td style="width:10%">手机号</td>
	 	<td colspan="2"><input type="text" value="${allvisitantmessage.visitantMessage.visitant_Phone }" disabled></td>
	 </tr>
	  <tr>
	  	<td style="width:10%">来访事由</td>
	 	<td colspan="2"><input type="text" value="${allvisitantmessage.visitantMessage.visitant_Matter }" disabled></td>
	 	<td style="width:10%">车牌号</td>
	 	<td colspan="2"><input type="text" value="${allvisitantmessage.visitantMessage.visitant_Carno }" disabled></td>
	 </tr>
	  <tr>
	  	<td style="width:10%">人&nbsp;&nbsp;数</td>
	 	<td colspan="2"><input type="text" value="${allvisitantmessage.visitantMessage.visitant_Num }" disabled></td>
	 	<td style="width:10%">出生年月</td>
	 	<td colspan="2"><input type="text" value="${allvisitantmessage.visitantMessage.visitant_Birth }" disabled></td>
	 </tr>
	  <tr>
	 	<td colspan="7">备&nbsp;&nbsp;注<input type="text" size="74" value="${allvisitantmessage.visitantMessage.visitant_Notes }" disabled></td>
	 </tr>
	</table>
	<table class="public-cont-table">
		<tr>
			<td colspan="3" style="width:50%">姓&nbsp;&nbsp;名<input type="text" size="30" value="${allvisitantmessage.employees.employees_Name }" disabled></td>
	 		<td colspan="3" style="width:50%">部&nbsp;&nbsp;门<input type="text" size="30" value="${allvisitantmessage.department.department_Name }" disabled></td>
		</tr>
		<tr>
			<td colspan="3" style="width:50%">单&nbsp;&nbsp;位<input type="text" size="30" value="${allvisitantmessage.company.company_Name }" disabled></td>
	 		<td colspan="3" style="width:50%">手&nbsp;&nbsp;机<input type="text" size="30" value="${allvisitantmessage.employees.employees_Phone }" disabled></td>
		</tr>
	</table>
	</div>
</body>
</html>