<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>论文匹配管理</title>
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
		
		function upload(){
	        var form = new FormData(document.getElementById("inputForm"));
	        $.ajax({
	            url:"${ctx}/model/lwModel/upload",
	            type:"post",
	            data:form,
	            processData:false,
	            contentType:false,
	            success:function(data){
	                if(data.flag){
	                    $("#filepath").val(data.data);
	                    alert("导入成功")
	                }else {
	                    alert("上传失败")
	                }
	            }
	        });
	  }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/lwmatch/lwMatch/form?id=${lwMatch.id}">论文匹配<shiro:hasPermission name="lwmatch:lwMatch:edit">${not empty lwMatch.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lwmatch:lwMatch:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lwMatch" action="${ctx}/lwmatch/lwMatch/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">匹配模型：</label>
			<div class="controls">
				<form:checkboxes path="modelid" items="${lmlist}" itemLabel="modelname" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文档名称：</label>
			<div class="controls">
				<form:input path="filename" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">作者：</label>
			<div class="controls">
				<form:input path="author" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文档上传：</label>
			<div class="controls">
				<form:input path="filepath" type="hidden" htmlEscape="false" id="filepath" maxlength="500" class="input-xlarge required"/>
				<input type="file" id="apkFile" name="apkFile"/>
				<input id="btnImport" class="btn btn-primary" type="button" onclick="upload()" value="导入"/></li>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lwmatch:lwMatch:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>