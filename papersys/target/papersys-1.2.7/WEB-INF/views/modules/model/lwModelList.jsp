<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>论文模型管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“doc”或“docx”格式文件！"});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sys/user/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/model/lwModel/">论文模型列表</a></li>
		<shiro:hasPermission name="model:lwModel:edit"><li><a href="${ctx}/model/lwModel/form">论文模型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lwModel" action="${ctx}/model/lwModel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模型名称：</label>
				<form:input path="modelname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>文件名称：</label>
				<form:input path="filename" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模型名称</th>
				<th>文件名称</th>
				<th>创建时间</th>
				<th>文件说明</th>
				<shiro:hasPermission name="model:lwModel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lwModel">
			<tr>
				<td><a href="${ctx}/model/lwModel/form?id=${lwModel.id}">
					${lwModel.modelname}
				</a></td>
				<td>
					${lwModel.filename}
				</td>
				<td>
					<fmt:formatDate value="${lwModel.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lwModel.remarks}
				</td>
				<shiro:hasPermission name="model:lwModel:edit"><td>
    				<a href="${ctx}/model/lwModel/form?id=${lwModel.id}">修改</a>
					<a href="${ctx}/model/lwModel/delete?id=${lwModel.id}" onclick="return confirmx('确认要删除该论文模型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>