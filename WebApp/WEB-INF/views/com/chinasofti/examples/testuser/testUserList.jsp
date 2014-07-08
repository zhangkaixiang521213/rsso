<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/include/default.jsp" %>

<html>
<head>
	<title>用户管理</title>
</head>

<body>

	 <div >
			<form class="form-search" action="#" >
				<label>登录名：</label> <input type="text" name="search_loginName" class="input-medium" value="${param.search_loginName}"> 
				<label>姓     名：</label> <input type="text" name="search_name" class="input-medium" value="${param.search_name}"> 
				<button type="submit" class="btn" id="search_btn">查询</button>
		    </form>
	 </div>
	<div class="control-group">
		<div class="controls">
			<a class="btn" href="${ctx}/testuser/toAddPage">增加</a>
		</div>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>登录名</th><th>用户名</th><th>注册时间<th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a href="${ctx}/testuser/toDetailPage/${user.id}">${user.loginName}</a></td>
				<td>${user.name}</td>
				<td>
					<fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
				</td>
				<td><a href="${ctx}/testuser/toUpdatePage/${user.id}">修改</a>&nbsp; <a href="${ctx}/testuser/delete/${user.id}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${page}" paginationSize="${paginationSize}"/>
</body>
</html>
