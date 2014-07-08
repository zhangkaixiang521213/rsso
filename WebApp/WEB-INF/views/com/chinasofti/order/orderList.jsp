<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/include/default.jsp" %>
<html>
<head>
	<title>用户管理</title>
</head>

<body>
	 <div >
			<form class="form-search" action="#" >
				<label>用     户：</label> <input type="text" name="search_oname" class="input-medium" value="${param.search_oname}"> 
				<label>地     址：</label> <input type="text" name="search_oaddress" class="input-medium" value="${param.search_oaddress}"> 
				<button type="submit" class="btn" id="search_btn">查询</button>
		    </form>
	 </div>
	<div class="control-group">
		<div class="controls">
			<a class="btn" href="${ctx}/order/toAddPage">增加</a>
		</div>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>用户</th><th>地址</th><th>联系电话</th></tr></thead>
		<tbody>
		<c:forEach items="${orders}" var="order">
			<tr>
				<td><a href="${ctx}/order/toDetailPage/${order.orderId}">${order.oname}</a></td>
				<td>${order.oaddress}</td>
				<td>
					${order.phone}
				</td>
				<td><a href="${ctx}/order/toUpdatePage/${order.orderId}">修改</a>&nbsp; <a href="${ctx}/order/delete/${order.orderId}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${page}" paginationSize="${paginationSize}"/>
</body>
</html>
