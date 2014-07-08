<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/include/default.jsp" %>

<html>
<head>
	<title>用户管理</title>
</head>

<body>
	<form id="inputForm"  class="form-horizontal">
		<input type="hidden" name="orderId" value="${order.orderId}"/>
		<fieldset>
			<legend><small>用户订单</small></legend>
			<table border=0>
				<tr>
					<td>
						<label for="loginName" class="control-label" style="width:80px;text-align:left">用户:</label>
						<div class="controls"  style="margin-left:90px;">
							<input type="text" id="oname" name="oname" value="${order.oname }" class="input-large required" disabled="" />
						</div>
					</td>
					<td>
						<label for="name" class="control-label" style="width:80px;text-align:left">地址:</label>
						<div class="controls" style="margin-left:90px;">
							<input type="text" id="oaddress" name="oaddress"  value="${order.oaddress }"  class="input-large required" disabled="" />
						</div>
					</td>
					<td>
						<label for="plainPassword" class="control-label"  style="width:80px;text-align:left">联系电话:</label>
						<div class="controls" style="margin-left:90px;">
							<input type="text" id="phone" name="phone"  value="${order.phone }"  class="input-large required" disabled="" />
						</div>
					</td>
				</tr>
			</table>
			<br/>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead><tr><th>商品名称</th><th>价格</th><th>数量</th></tr></thead>
				<tbody>
					<c:forEach items="${orderItems}" var="orderItem" varStatus="status">
						<tr>
							<input type="hidden"  name="orderItems[${status.index}].itemId" value="${orderItem.itemId}"" disabled="" />
							<input type="hidden"  name="orderItems[${status.index}].orderId" value="${orderItem.orderId}"" disabled="" />
							<td><input type="text" name="orderItems[${status.index}].itemName" value="${orderItem.itemName}" class="input-large" disabled="" /></td>
							<td><input type="text" name="orderItems[${status.index}].price" value="${orderItem.price}" class="input-large" disabled="" /></td>
							<td><input type="text" name="orderItems[${status.index}].acount" value="${orderItem.acount}" class="input-large" disabled="" /></td>					
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tags:pagination page="${page}" paginationSize="${paginationSize}"/>
			
			<div class="form-actions">
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="window.location.href='${ctx}/order/list';"/>
			</div>
		</fieldset>
	</form>
</body>
</html>
