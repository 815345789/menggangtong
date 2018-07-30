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
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/public.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>  
	<script type="text/javascript">
	function power(obj){
		if(obj!=9){
			window.location.replace("login.jsp");
		}
	}
	$(document.body).ready(function(){
		$.get(
				"selectCount.do",
				function(date){
					$("#item-warrp-all").text("今日访问总人数："+date[0]);
					$("#item-warrp-in").text("未离开人数："+date[1]);
					$("#item-warrp-out").text("已离开人数："+date[2]);
				},"json"
				);
	})
	</script>
</head>
<body onload="power('${sessionScope.userMessage.power_Num}')">
<div class="public-header-warrp">
	<div class="public-header">
		<div class="content">
			<div class="public-header-logo"><a href=""><i>LOGO</i>
			<h3>铖安</h3></a></div>
			<div class="public-header-admin fr">
				<p class="admin-name">${userMessage["username"] } 您好！</p>
				<div class="public-header-fun fr">
					<a href="" class="public-header-loginout">安全退出</a>	
				</div>
			</div>
		</div>
	</div>
</div>
<div class="clearfix"></div>
<!-- 内容展示 -->
<div class="public-ifame mt20">
	<div class="content">
	<!-- 内容模块头 -->
		<div class="public-ifame-header">
			<ul>
				<li class="ifame-item"><div class="item-warrp"><span id="item-warrp-all">今日访问总人数：${all }</span></div></li>
				<li class="ifame-item"><div class="item-warrp"><span id="item-warrp-in">未离开人数：${in }</span></div></li>
				<li class="ifame-item"><div class="item-warrp"><span id="item-warrp-out">已离开人数：${out }</span></div></li>
			</ul>
		</div>
		<div class="clearfix"></div>
		<!-- 左侧导航栏 -->
		<div class="public-ifame-leftnav">
			<div class="public-title-warrp">
				<div class="public-ifame-title ">
					<a href="">首页</a>
				</div>
			</div>
			<ul class="left-nav-list">
				<li class="public-ifame-item">
					<a href="javascript:;">来访登记</a>
					<div class="ifame-item-sub">
						<ul>
							<li><a href="" target="content">现场登记</a></li>
						</ul>
					</div>
				</li>
				<li class="public-ifame-item">
					<a href="javascript:;">记录查询</a>
					<div class="ifame-item-sub">
						<ul>
							<li><a href="visitantList.do" target="content">记录查询</a></a></li>
						</ul>
					</div>
				</li>
				<li class="public-ifame-item">
					<a href="javascript:;">人员管理</a>
					<div class="ifame-item-sub">
						<ul>
							<li><a href="companylist.do" target="content">公司管理</a></li>
							<li><a href="employeeslist.do" target="content">职员管理</a></li>
							<li><a href="blacklists.do" target="content">黑名单管理</a></li>
						</ul>
					</div>
				</li>
				<li class="public-ifame-item">
					<a href="javascript:;">系统菜单</a>
					<div class="ifame-item-sub">
						<ul>
							<li><a href="usermessagelist.do" target="content">账户操作</a></a></li>
							<li><a href="equipmentlist.do" target="content">设备管理</a></a></li>
							<li><a href="usingsitelist.do" target="content">区域管理</a></a></li>
						</ul>
					</div>
				</li>
			</ul>
		</div>
		<!-- 右侧内容展示部分 -->
		<div class="public-ifame-content">
		<iframe name="content" src="main.html" frameborder="0" id="mainframe" scrolling="yes" marginheight="0" marginwidth="0" width="100%" style="height: 700px;"></iframe>
		</div>
	</div>
</div>
<script src="js/jquery.min.js"></script>
<script>
$().ready(function(){
	var item = $(".public-ifame-item");

	for(var i=0; i < item.length; i++){
		$(item[i]).on('click',function(){
			$(".ifame-item-sub").hide();
			if($(this.lastElementChild).css('display') == 'block'){
				$(this.lastElementChild).hide()
				$(".ifame-item-sub li").removeClass("active");
			}else{
				$(this.lastElementChild).show();
				$(".ifame-item-sub li").on('click',function(){
					$(".ifame-item-sub li").removeClass("active");
					$(this).addClass("active");
				});
			}
		});
	}
});
</script>
</body>
</html>