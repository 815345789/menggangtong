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
	// 验证中文名称
	function isChinaName(name) {
	 var pattern = /^[\u4E00-\u9FA5]{1,6}$/;
	 return pattern.test(name);
	}
	 
	// 验证手机号
	function isPhoneNo(phone) { 
	 var pattern = /^1[34578]\d{9}$/; 
	 return pattern.test(phone); 
	}
	 
	// 验证身份证 
	function isCardNo(card) { 
	 var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
	 return pattern.test(card); 
	} 
	 
	// 验证函数
	function addblacks() {
	 var str = '';
	 
	 // 判断名称
	 if($.trim($('#name').val()).length == 0) {
	  str += '姓名没有输入\n';
	  $('#name').focus();
	 } else {
	  if(isChinaName($.trim($('#name').val())) == false) {
	   str += '名称不合法\n';
	   $('#name').focus();
	  }
	 }
	 
	 // 判断手机号码
	 if ($.trim($('#phone').val()).length == 0) { 
	  str += '手机号没有输入\n';
	  $('#phone').focus();
	 } else {
	  if(isPhoneNo($.trim($('#phone').val()) == false)) {
	   str += '手机号码不正确\n';
	   $('#phone').focus();
	  }
	 }
	 
	 // 验证身份证
	 if($.trim($('#identity').val()).length == 0) { 
	  str += '身份证号码没有输入\n';
	  $('#identity').focus();
	 } else {
	  if(isCardNo($.trim($('#identity').val())) == false) {
	   str += '身份证号不正确；\n';
	   $('#identity').focus();
	  }
	 }
	 
	 // 验证地址
	 if($.trim($('#nation').val()).length == 0) { 
	  str += '民族没有输入\n';
	  $('#address').focus();
	 }
	 
	 // 如果没有错误则提交
	 if(str != '') {
	  alert(str);
	 } else {
	  sumbitblacks();
	 }
	}
		function sumbitblacks(){
			document.addblack.action = "addblack.do";
		 	document.addblack.submit();
		 	window.opener.location.href = "blacklists.do";
		}
		function closewin(){
			window.close();
		}
	</script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">人员管理</a><a href="">黑名单管理</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>添加黑名单信息</h3>
			</div>
			<form id="addblack" name="addblack" method="get" action="addblack.do" >
			<div class="public-content-cont two-col">
				<div class="public-cont-left">
					<div class="form-group mt0">
						<label for="">姓名</label>
						<input type="text" value="" id="name" name="blacklist_Name" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">性别</label>
						<select name="blacklist_sex">
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</div>
					<div class="form-group mt0">
						<label for="">民族</label>
						<input type="text" value="" id="nation" name="blacklist_Nation" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">身份证号</label>
						<input type="text" value="" id="identity" name="blacklist_Cardno" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">手机号</label>
						<input type="text" value="" id="phone" name="blacklist_Phone" class="form-input-small">
					</div>
					<div class="form-group mt0">
						<label for="">备注</label>
						<input type="text" value="" name="blacklist_Notes" class="form-input-small">
					</div>
					<div class="form-group mt0" style="text-align:center;margin-top:15px;">
						<input type="button" class="sub-btn" value="提    交" onclick="addblacks()" >
						<input type="button" class="sub-btn" value="关   闭" onclick="closewin()">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>