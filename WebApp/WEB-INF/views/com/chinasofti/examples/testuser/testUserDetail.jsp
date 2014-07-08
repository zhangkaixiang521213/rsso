<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/include/default.jsp" %>

<html>
<head>
	<title>用户管理</title>
</head>

<body>
	<form id="inputForm"  class="form-horizontal">
		<input type="hidden" name="id" value="${user.id}"/>
		<fieldset>
			<legend><small>用户管理</small></legend>
			<div class="control-group">
				<label class="control-label">登录名:</label>
				<div class="controls">
					<input type="text" value="${user.loginName}" class="input-large" disabled="" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" value="${user.name}" class="input-large" disabled="" />
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="plainPassword" name="plainPassword" class="input-large" disabled="" />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">注册日期:</label>
				<div class="controls">
					<span class="help-inline" style="padding:5px 0px"><fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" /></span>
				</div>
			</div>
			<div class="form-actions">
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	
</body>
</html>
