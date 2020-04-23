<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>论文匹配管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/lwmatch/lwMatch/">论文匹配列表</a></li>
		<shiro:hasPermission name="lwmatch:lwMatch:edit"><li><a href="${ctx}/lwmatch/lwMatch/form">论文匹配添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lwMatch" action="${ctx}/lwmatch/lwMatch/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模型名称：</label>
				<form:input path="modelname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>文档名称：</label>
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
				<th>文档名称</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="lwmatch:lwMatch:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lwMatch">
			<tr>
				<td><a href="${ctx}/lwmatch/lwMatch/form?id=${lwMatch.id}">
					${lwMatch.modelname}
				</a></td>
				<td>
					${lwMatch.filename}
				</td>
				<td>
					<fmt:formatDate value="${lwMatch.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lwMatch.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lwMatch.remarks}
				</td>
				<shiro:hasPermission name="lwmatch:lwMatch:edit"><td>
    				<a href="${ctx}/lwmatch/lwMatch/lwmatch?id=${lwMatch.id}" onclick="return confirmx('查重时间可能需要一段时间，请等候……', this.href)">开始查重</a>
					<a href="${ctx}/lwmatch/lwMatch/delete?id=${lwMatch.id}" onclick="return confirmx('确认要删除该论文匹配吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>