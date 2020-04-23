<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>论文模型管理</title>
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
		<li><a href="${ctx}/model/lwModel/">论文模型列表</a></li>
		<li class="active"><a href="${ctx}/model/lwModel/form?id=${lwModel.id}">论文模型<shiro:hasPermission name="model:lwModel:edit">${not empty lwModel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="model:lwModel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lwModel" action="${ctx}/model/lwModel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模型名称：</label>
			<div class="controls">
				<form:input path="modelname" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件名称：</label>
			<div class="controls">
				<form:input path="filename" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件路径：</label>
			<div class="controls">
				<form:input path="filepath" type="hidden" htmlEscape="false" id="filepath" maxlength="500" class="input-xlarge required"/>
				<input type="file" id="apkFile" name="apkFile"/>
				<input id="btnImport" class="btn btn-primary" type="button" onclick="upload()" value="导入"/></li>
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件说明：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="model:lwModel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>