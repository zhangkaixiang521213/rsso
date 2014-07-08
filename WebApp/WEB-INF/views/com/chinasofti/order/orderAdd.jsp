<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/static/include/default.jsp" %>

<html>
<head>
	<title>用户注册</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/order/save" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>用户订单</small></legend>
			<table border=0>
				<tr>
					<td>
						<label for="loginName" class="control-label" style="width:80px;text-align:left">用户:</label>
						<div class="controls"  style="margin-left:90px;">
							<input type="text" id="oname" name="oname" class="input-large required"/>
						</div>
					</td>
					<td>
						<label for="name" class="control-label" style="width:80px;text-align:left">地址:</label>
						<div class="controls" style="margin-left:90px;">
							<input type="text" id="oaddress" name="oaddress" class="input-large required"/>
						</div>
					</td>
					<td>
						<label for="plainPassword" class="control-label" style="width:80px;text-align:left">联系电话:</label>
						<div class="controls" style="margin-left:90px;">
							<input type="text" id="phone" name="phone" class="input-large required"/>
						</div>
					</td>
				</tr>
			</table>
			<div class="control-group">
				<div class="control-label"  style="text-align:left;width:300px;">
					<a class="btn" href="#" onclick="addRow();">增加</a>
					<a class="btn" href="#" onclick="delRow();">删除</a>
				</div>
			</div>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead><tr><th style="width:30px;">选择</th><th>商品名称</th><th>价格</th><th>数量</th></tr></thead>
				<tbody id="sublist">
					<tr>
						<td><input type="checkbox" name="rowCheckbox" value="${orderItem.itemId}"></td>
						<td><input type="text" name="orderItems[0].itemName" class="input-large"/></td>
						<td><input type="text"  name="orderItems[0].price" class="input-large"/></td>
						<td><input type="text"  name="orderItems[0].acount" class="input-large"/></td>
					</tr>
				</tbody>
			</table>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
			
		</fieldset>
		
	</form>
	
	<script>
		$(document).ready(function() {
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
		
		/**
		*添加记录
		*/
		function addRow(){
			var rowLength = $("#sublist tr").length;
			$("#sublist").append('<tr>'+
					'<td><input type="checkbox" name="rowCheckbox"></td>'+
					'<td><input type="text" name="orderItems['+rowLength+'].itemName" class="input-large"/></td>'+
					'<td><input type="text" name="orderItems['+rowLength+'].price" class="input-large"/></td>'+
					'<td><input type="text" name="orderItems['+rowLength+'].acount" class="input-large"/></td>'+
				'</tr>');
		}
		/**
		*删除记录
		*/
		function delRow(){
			var isDel=false;
			$("input[name='rowCheckbox']:checkbox:checked").each(function(index){
				 $(this).parent("td").parent("tr").remove();
				 isDel=true;
			});
			if(!isDel) return;
			 $("#sublist tr").each(function(rowIndex){
				$(this).find("input").each(function(index){
					
					var name=$(this).attr("name");
					if(name.indexOf('[')!=-1 && name.indexOf(']')!=-1){
						var nameIndex=name.substring(0,name.indexOf('[')+1)+rowIndex+name.substring(name.indexOf(']'),name.length);
						$(this).attr("name",nameIndex);
					}
				});
			 });
		};
	</script>
</body>
</html>
