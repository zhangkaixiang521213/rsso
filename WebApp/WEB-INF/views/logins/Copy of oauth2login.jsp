<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title>登录页面</title>
	<!-- 
	<link rel="stylesheet" type="text/css" href="${ctx}/loginStatic/css/login.css"/>
	<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
	 -->
	
</head>

<body>
<form action="" method="post">
    用户名：<input type="text" name="loginName" value=""><br/>
    密码：<input type="password" name="loginPwd"><br/>
    <input type="submit" value="登录">
</form>

</body>
</html>
