<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="${ctx}/staticOauth/css/bootstrap.min.css" rel="stylesheet" media="screen">

<link href="${ctx}/staticOauth/css/login.css" rel="stylesheet" media="screen">

<script src="${ctx}/staticOauth/js/bootstrap.min.js"></script>
<title>云测试</title>
<style type="text/css">
body, html{
	background:#ededed;
}
.cellphone{
	height:46px;
	background: url(staticOauth/images/cellphone.gif) no-repeat;
	margin-left: 620px;
}
</style>
</head>
<body>
<div class="container">
	<div class="cellphone"></div>
</div>
<div class="header">
	<div class="container">
		<div class="login_logo"></div>
		<div class="logotitle">云测试</div>
	</div>
</div>
<div class="mainbody">
	<div class="container">
		<div class="span6 main-left">
			<img src="staticOauth/images/login_bg.jpg">
		</div>
		<div class="span5">
		<!-- 登陆框开始 -->
			<div class="login_wrapper">
				<div class="border_left"></div>
				<div class="login">
				<form name="loginFrm" class="form-horizontal" id="loginFrm" style="margin: 20px 0px 24px;" action="" method="post" novalidate="novalidate">
			    	<input name="linkpage" id="linkpage" type="hidden" value="">
			  		<div class="control-group" style="margin-bottom: 10px;">
						<label class="control-label">用户名：</label>
						<div class="controls">
							<input name="loginName" id="loginName" style="width: 150px; height: 26px; font-size: 13px;" onkeydown="keyDownEvent(event)" type="text" value="" mainid="j_username">
						</div>
					</div>
	  	
				  	<div class="control-group" style="margin-bottom: 10px;">
						<label class="control-label">密&nbsp;&nbsp;码：</label>
						<div class="controls">
							<input name="loginPwd" id="loginPwd" style="width: 150px; height: 26px; font-size: 13px;" onkeydown="keyDownEvent(event)" type="password">
							<!--
							<a id="forget-password" href="">忘记密码?</a>
							-->
						</div>
					</div>

					<div class="control-group">
				      	<div class="controls">
				      		<input  class="btn btn-large btn-primary" id="submit_button" style="width: 170px;"  type="submit" value="登录">
				      	</div>
					</div>
	  			</form>
				</div>
				<div class="border_right"></div>
	   		</div>
		<!-- 登陆框结束 -->
		</div>
	</div>
</div>
<div class="container">
	<div class="span5 offset6">
	<!-- 错误信息 -->
		<div class="errorfield" id="msg" style="display: none;">
		</div>
	</div>
</div>
<div class="container">
	<div class="description"></div>
</div>
<div class="footer">
	<div class="copyright">版权所有 © 2012-2015 北京中软国际信息技术有限公司 </div>
</div>
<div class="bottom-border"></div>

</body></html>