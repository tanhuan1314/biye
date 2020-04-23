<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单编号规则管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<h3>订单编号规则</h3><br/>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="csOrderSerialRule" action="${ctx}/sys/csOrderSerialRule/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">公司简码：</label>
			<div class="controls">
				<form:input path="orgCode" htmlEscape="false" maxlength="50" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">连接符1：</label>
			<div class="controls">
				<form:input path="symbol1" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">店铺简码：</label>
			<div class="controls">
				<form:input path="shopCode"  htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">连接符2：</label>
			<div class="controls">
				<form:input path="symbol2" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">日期格式：</label>
			<div class="controls">
				<form:select path="dateFormat" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('date_format_list')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">连接符3：</label>
			<div class="controls">
				<form:input path="symbol3" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls" style="padding-top: 4px">
				001-999(自动生成)
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">例：</label>
			<div class="controls" style="padding-top: 4px">
			<span class="help-inline">
			<font color="red">
				CHUANSHI(公司简码)-(连接符1)CS(店铺简码)-(连接符2)1111(日期)-(连接符3)001(订单序号)</br>
			</font> 
			</span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:csOrderSerialRule:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>